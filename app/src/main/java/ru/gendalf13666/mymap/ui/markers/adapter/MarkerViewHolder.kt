package ru.gendalf13666.mymap.ui.markers.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.gendalf13666.mymap.databinding.MarkerItemBinding
import ru.gendalf13666.mymap.domain.models.MarkerDomain
import ru.gendalf13666.mymap.utils.extensions.click
import ru.gendalf13666.mymap.utils.extensions.longClick

class MarkerViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: MarkerItemBinding by viewBinding()

    fun bind(marker: MarkerDomain, delegate: MarkerAdapter.Delegate?) {
        with(viewBinding) {
            coordinate.text = marker.coordinateToString()
            title.text = marker.title
            description.text = marker.description
            root.click { delegate?.onItemClick(marker) }
            root.longClick {
                delegate?.onItemLongClick(marker)
                true
            }
        }
    }
}
