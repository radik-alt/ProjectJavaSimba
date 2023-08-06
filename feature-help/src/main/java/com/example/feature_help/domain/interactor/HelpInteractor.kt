package com.example.feature_help.domain.interactor

import android.content.Context
import com.example.core.entity.CategoriesEntity
import com.example.feature_help.data.repository.HelpRepository
import com.example.feature_help.domain.usecase.HelpUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HelpInteractor @Inject constructor(
    private val repository: HelpRepository
) : HelpUseCase {
    override suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity> =
        repository.getCategory(context, newSession)
}