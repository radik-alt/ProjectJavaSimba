package com.example.projectjavasimba.domain_impl.interactor

import android.content.Context
import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.domain.usecase.NewsUseCase

class NewsInteractor(
    private val repository: NewsRepository
) : NewsUseCase {
    override fun getEvents(context: Context) =
        repository.getEvents(context)
}