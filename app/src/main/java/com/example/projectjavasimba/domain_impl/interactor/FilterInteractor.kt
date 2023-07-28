package com.example.projectjavasimba.domain_impl.interactor

import android.content.Context
import com.example.projectjavasimba.data.repository.FilterRepository
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.usecase.FilterUseCase
import kotlinx.coroutines.flow.Flow

class FilterInteractor(
    private val repository: FilterRepository
): FilterUseCase {
    override suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity> =
        repository.getCategory(context, newSession)
}