package uz.gita.composeexample2.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.composeexample2.data.source.room.dao.GroupDao
import uz.gita.composeexample2.data.source.room.dao.StudentDao
import uz.gita.composeexample2.data.source.room.entities.GroupEntity
import uz.gita.composeexample2.data.source.room.entities.StudentEntity

@Database(
    entities = [
        GroupEntity::class,
        StudentEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun groupDao(): GroupDao

    abstract fun studentDao(): StudentDao

}