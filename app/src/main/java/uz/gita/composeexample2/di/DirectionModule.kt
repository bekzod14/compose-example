package uz.gita.composeexample2.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.composeexample2.ui.group.GroupScreenDirection
import uz.gita.composeexample2.ui.group.GroupScreenDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {
    @Binds
    fun bindGroupScreenDirection(impl: GroupScreenDirectionImpl): GroupScreenDirection
}