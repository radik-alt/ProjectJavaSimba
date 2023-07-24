package com.example.projectjavasimba.domain_impl.interactor

import android.content.Context
import com.example.projectjavasimba.data.repository.HelpRepository
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.usecase.HelpUseCase
import kotlinx.coroutines.flow.Flow

class HelpInteractor(
    private val repository: HelpRepository
) : HelpUseCase {
    override suspend fun getCategory(context: Context): Flow<CategoriesEntity> =
        repository.getCategory(context)
}