package com.example.projectjavasimba.domain_impl.interactor

import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.domain.usecase.NewsUseCase

class NewsInteractor(
    private val repository: NewsRepository
) : NewsUseCase {

    override suspend fun getEvents() = repository.getEvents()

}