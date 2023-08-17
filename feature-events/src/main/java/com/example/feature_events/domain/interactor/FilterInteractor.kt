package com.example.feature_events.domain.interactor

import android.content.Context
import com.example.core.entity.CategoriesEntity
import com.example.feature_events.data.repository.FilterRepository
import com.example.feature_events.domain.usecase.FilterUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterInteractor @Inject constructor(
    private val repository: FilterRepository
): FilterUseCase {
    override suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity> =
        repository.getCategory(context, newSession)
}