package uz.gita.composeexample2.group
//import  com.google.common.truth.Truth.assertThat

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uz.gita.composeexample2.data.repository.GroupRepository
import uz.gita.composeexample2.data.source.room.entities.GroupEntity

class GroupRepoTest {

    private val a by lazy { }
    private lateinit var repo: GroupRepository

    @Before
    fun start() {
        //Testlar boshlanishidan avval ishlaydi
        repo = MockGroupRepository()
    }

    @After
    fun end() {
        //Testlar tugagandan keyn ishlaydi
    }

    @Test
    fun `add_group_data_with_empty_name`() = runBlocking {
        val data = repo.getGroupsSynchronous()
        val oldSize = data.size
        repo.addGroup(GroupEntity(1, ""))
        assert(data.size == oldSize)

    }

    @Test
    fun `add_group_data_with_invalid_id`() = runBlocking {
        val data = repo.getGroupsSynchronous()
        val oldSize = data.size
        repo.addGroup(GroupEntity(0, "Hello"))
        assert(data.size == oldSize)

    }

    @Test
    fun `add_group_data_with_valid_data`() = runBlocking {
        val data = repo.getGroupsSynchronous()
        val oldSize = data.size
        repo.addGroup(GroupEntity(1, "Hello"))
        assert(data.size == oldSize + 1)
    }

}