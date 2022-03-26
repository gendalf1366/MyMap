package ru.gendalf13666.mymap.utils.extensions

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.showSnakeBar(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun View.click(click: () -> Unit) = setOnClickListener { click() }

fun View.longClick(longClick: () -> Boolean) = setOnLongClickListener { longClick() }

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}
