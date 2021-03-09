package com.example.shoppinglist.di

import com.example.shoppinglist.repository.ItemRepository
import com.example.shoppinglist.repository.ItemRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(itemRepository: ItemRepository) : ItemRepositoryInterface
}