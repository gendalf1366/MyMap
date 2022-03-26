package ru.gendalf13666.mymap.domain.models

import com.yandex.mapkit.geometry.Point

data class NewMarkerResult(
    val result: Boolean,
    val position: Point
) : DataResult
