package ru.gendalf13666.mymap.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.gendalf13666.mymap.data.storage.entity.Marker

@Database(
    exportSchema = true,
    entities = [Marker::class],
    version = 2
)
abstract class Storage : RoomDatabase() {
    abstract fun storageDao(): StorageDao
}
