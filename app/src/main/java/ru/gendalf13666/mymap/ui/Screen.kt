package ru.gendalf13666.mymap.ui

interface Screen {
    fun loading(isLoading: Boolean)
    fun showError(throwable: Throwable)
}
