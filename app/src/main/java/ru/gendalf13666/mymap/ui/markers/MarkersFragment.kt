package ru.gendalf13666.mymap.ui.markers

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gendalf13666.mymap.R
import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain
import ru.gendalf13666.mymap.domain.models.MarkersResult
import ru.gendalf13666.mymap.ui.Publisher
import ru.gendalf13666.mymap.ui.Screen
import ru.gendalf13666.mymap.ui.UpdateObserver
import ru.gendalf13666.mymap.ui.Updater
import ru.gendalf13666.mymap.ui.edit.EditFragment
import ru.gendalf13666.mymap.ui.markers.adapter.MarkerAdapter
import ru.gendalf13666.mymap.ui.markers.base.BaseMarkersFragment
import ru.gendalf13666.mymap.utils.extensions.showSnakeBar

class MarkersFragment : BaseMarkersFragment(), Screen, MarkerAdapter.Delegate, UpdateObserver {

    private val adapter by lazy { MarkerAdapter(this) }
    private var publisher: Publisher? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        publisher = (context as Updater).getUpdater()
        publisher?.subscribe(this)
    }

    override fun onDetach() {
        super.onDetach()
        publisher?.unsubscribe(this)
    }

    override fun loading(isLoading: Boolean) {
        viewBinding.progress.isVisible = isLoading
    }

    override fun showError(throwable: Throwable) {
        viewBinding.root.showSnakeBar(throwable.localizedMessage)
    }

    override fun onItemClick(marker: MarkerDomain) {}

    override fun onItemLongClick(marker: MarkerDomain) {
        showActionsDialog(marker)
    }

    private fun showActionsDialog(marker: MarkerDomain) {
        AlertDialog.Builder(requireContext())
            .setItems(resources.getStringArray(R.array.item_actions)) { _, item ->
                markerAction(item, marker)
            }
            .show()
    }

    private fun markerAction(item: Int, marker: MarkerDomain) {
        when (item) {
            ACTION_MOVE_TO_MARKER -> moveToMarker(marker)
            ACTION_EDIT_MARKER -> editMarker(marker)
            ACTION_DELETE_MARKER -> viewModel.removeMarker(markerId = marker.markerId)
        }
    }

    private fun editMarker(marker: MarkerDomain) {
        val editFragment: EditFragment = EditFragment.newInstance()
        editFragment.arguments = bundleOf().apply { putInt(KEY_MARKER_ID, marker.markerId) }
        editFragment.isCancelable = false
        editFragment.show(
            requireActivity().supportFragmentManager,
            EditFragment.TAG
        )
    }

    private fun moveToMarker(marker: MarkerDomain) {
        NavHostFragment.findNavController(this).also { controller ->
            controller.previousBackStackEntry?.savedStateHandle
                ?.set(KEY_MARKER_LON, marker.longitude)
            controller.previousBackStackEntry?.savedStateHandle
                ?.set(KEY_MARKER_LAT, marker.latitude)
            controller.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerSetting()

        viewModel.getMarkers()

        viewModel.getOperationLiveData()
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }
    }

    private fun initRecyclerSetting() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewBinding.recyclerView.also { recycler ->
            recycler.layoutManager = linearLayoutManager
            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
        }
    }

    private fun renderData(result: AppState) {
        when (result) {
            is AppState.Error -> loading(false)
            is AppState.Loading -> loading(true)
            is AppState.Success -> {
                loading(false)
                renderSuccess(result)
            }
        }
    }

    private fun renderSuccess(result: AppState.Success) {
        when (val marker = result.data) {
            is MarkersResult -> adapter.setItems(marker.result)
        }
    }

    override fun updateMarkers() {
        viewModel.getMarkers()
    }

    companion object {
        const val KEY_MARKER_ID = "markerId"
        const val KEY_MARKER_LAT = "markerLat"
        const val KEY_MARKER_LON = "markerLon"

        const val ACTION_MOVE_TO_MARKER = 0
        const val ACTION_EDIT_MARKER = 1
        const val ACTION_DELETE_MARKER = 2
    }
}
