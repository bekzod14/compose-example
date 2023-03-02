package uz.gita.composeexample2.roomdb

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uz.gita.composeexample2.data.source.room.AppDatabase
import uz.gita.composeexample2.data.source.room.dao.GroupDao
import uz.gita.composeexample2.data.source.room.entities.GroupEntity

@SmallTest
@RunWith(AndroidJUnit4::class)
class GroupDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var groupDao: GroupDao

    init {
        println("Init")
    }

    @Before
    fun start() {
        println("Start")
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        groupDao = database.groupDao()
    }

    @After
    fun end() {
        println("End")
        database.close()
    }

    @Test
    fun `add Group`() = runBlocking {
        val groupItem = GroupEntity(1, "Android 1")
        groupDao.add(groupItem)
        assert(groupDao.getGroups().contains(groupItem))
    }

    @Test
    fun `delete Group`() = runBlocking {
        val groupItem = GroupEntity(2, "Android 2")
        groupDao.add(groupItem)
        groupDao.delete(groupItem)
        assert(!groupDao.getGroups().contains(groupItem))
    }

    @Test
    fun `update Group`() = runBlocking {
        val groupItem = GroupEntity(1, "Android 1")
        groupDao.add(groupItem)
        val newItem = groupItem.copy(name = "Android 5")
        groupDao.update(newItem)
        assert(groupDao.getGroups().contains(newItem))
    }

}