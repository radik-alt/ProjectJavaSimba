package com.example.projectjavasimba.data_impl

import com.example.projectjavasimba.data.repository.FilterRepository
import com.example.projectjavasimba.repository.api.RetrofitBuilder

class FilterRepositoryImpl: FilterRepository {

    private val api = RetrofitBuilder.apiService

}