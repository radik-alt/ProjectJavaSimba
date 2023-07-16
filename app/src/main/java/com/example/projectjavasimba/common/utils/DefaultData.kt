package com.example.projectjavasimba.common.utils

import com.example.projectjavasimba.R
import com.example.projectjavasimba.domain.entity.Category
import com.example.projectjavasimba.domain.entity.EventEntity

class DefaultData {

    companion object {

//        val defaultEventEntity = EventEntity(
//            id = 0,
//            title = "Спонсоры отремонтируют школу-интернат",
//            description = "Дубовская школа-интернат для детей\u2028с ограниченными возможностями здоровья стала первой в области …",
//            listImage = listOf(R.drawable.img, R.drawable.img, R.drawable.img),
//            date = "Осталось 13 дней (21.09 – 20.10)",
//            street = "Санкт-Петербург, Кирочная улица,\nд. 50А, каб. 208",
//            phone = "+7 (937) 037 37-73\n+7 (937) 016 16-16",
//            email = "radik.app@yandex.ru",
//            category = listOf(
//                Category(
//                    id = 0,
//                    image = R.drawable.invalid_name,
//                    title = "Дети"
//                )
//            ),
//            listFriends = listOf(),
//            isRead = false
//        )

        val defaultListCategory = listOf(
            Category(1, R.drawable.invalid_name, "Дети"),
            Category(2, R.drawable.invalid_name2, "Взрослые"),
            Category(3, R.drawable.invalid_name3, "Пожилые"),
            Category(4, R.drawable.invalid_name4,"Животные"),
            Category(5, R.drawable.invalid_name5,"Мероприятия")
        )

    }

}