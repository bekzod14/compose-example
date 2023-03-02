package uz.gita.composeexample2.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.composeexample2.data.repository.GroupRepository
import uz.gita.composeexample2.data.repository.StudentRepository
import uz.gita.composeexample2.data.repository.impl.GroupRepositoryImpl
import uz.gita.composeexample2.data.repository.impl.StudentRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindGroupRepository(impl: GroupRepositoryImpl): GroupRepository

    @[Binds Singleton]
    fun bindStudentRepository(impl: StudentRepositoryImpl): StudentRepository

}