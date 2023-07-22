package com.example.projectjavasimba.data_impl

import android.content.Context
import com.example.projectjavasimba.data_impl.callable.MyCallableEvent
import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.domain.entity.EventEntity
import com.example.projectjavasimba.domain.entity.EventsEntity
import com.example.projectjavasimba.repository.api.RetrofitBuilder
import com.example.projectjavasimba.repository.dto.events.EventDto
import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable
import java.util.Date

class NewsRepositoryImpl : NewsRepository {

    private val api = RetrofitBuilder.apiService

    override fun getEvents(context: Context): Observable<EventsEntity> {
        return api.getEvents().flatMap {
            Observable.just(it.toEntity())
        }.onErrorResumeNext {
            Observable.just(
                EventsEntity(
                    MyCallableEvent(context).call().toList()
                )
            )
        }
    }

    private fun EventsDto.toEntity() = EventsEntity(
        events = this.eventDtos?.toEntityList() ?: arrayListOf()
    )

    private fun List<EventDto>.toEntityList() = map {
        EventEntity(
            id = it.id ?: -1,
            title = it.name ?: "",
            description = it.description ?: "",
            listImage = it.photos ?: arrayListOf(),
            createAt = it.createAt ?: Date(),
            street = it.address ?: "",
            status = it.status ?: -1,
            startDate = it.startDate ?: Date(),
            endDate = it.endDate ?: Date(),
            phone = it.phone ?: "",
            category = it.category ?: -1,
            isRead = false,
        )
    }


}