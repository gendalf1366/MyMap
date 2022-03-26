package ru.gendalf13666.mymap.data.mappers

import ru.gendalf13666.mymap.data.storage.entity.Marker
import ru.gendalf13666.mymap.domain.models.MarkerDomain

fun markerToDataLayer(target: MarkerDomain): Marker =
    Marker(
        markerId = target.markerId,
        latitude = target.latitude,
        longitude = target.longitude,
        visible = target.visible,
        title = target.title,
        description = target.description
    )
