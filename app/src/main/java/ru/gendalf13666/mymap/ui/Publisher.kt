package ru.gendalf13666.mymap.ui

class Publisher {
    private val observers: MutableList<UpdateObserver>

    fun subscribe(observer: UpdateObserver) {
        observers.add(observer)
    }

    fun unsubscribe(observer: UpdateObserver) {
        observers.remove(observer)
    }

    fun startUpdate() {
        for (observer in observers) {
            observer.updateMarkers()
        }
    }

    init {
        observers = ArrayList()
    }
}
