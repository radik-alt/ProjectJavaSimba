package com.example.projectjavasimba.domain

import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event

class GetEventUseCase {

    fun getEventUseCase(): List<Event> {
        return listOf(
            Event(
                id = 0,
                title = "Спонсоры отремонтируют школу-интернат",
                description = "Дубовская школа-интернат для детей\u2028с ограниченными возможностями здоровья стала первой в области …",
                listImage = listOf(R.drawable.img, R.drawable.img, R.drawable.img),
                date = "Осталось 13 дней (21.09 – 20.10)",
                street = "Санкт-Петербург, Кирочная улица,\nд. 50А, каб. 208",
                phone = "+7 (937) 037 37-73\n+7 (937) 016 16-16",
                email = "radik.app@yandex.ru",
                category = listOf(
                    Category(
                        id = 0,
                        image = R.drawable.invalid_name,
                        title = "Дети"
                    )
                ),
                listFriends = listOf()
            )
        )
    }

}