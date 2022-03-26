package ru.gendalf13666.mymap.ui.markers.base

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gendalf13666.mymap.R
import ru.gendalf13666.mymap.databinding.FragmentMarkersBinding
import ru.gendalf13666.mymap.ui.markers.MarkersViewModel

abstract class BaseMarkersFragment : Fragment(R.layout.fragment_markers) {
    protected val viewModel: MarkersViewModel by viewModel()
    protected val viewBinding: FragmentMarkersBinding by viewBinding()
}
