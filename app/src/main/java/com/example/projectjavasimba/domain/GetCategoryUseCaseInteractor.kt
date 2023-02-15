package com.example.projectjavasimba.domain

import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Category

class GetCategoryUseCaseInteractor {

    fun getCategoryUseCase():List<Category> {
        return listOf(
            Category(
                id = 0,
                image = R.drawable.invalid_name,
                title = "Дети"
            ),
            Category(
                id = 1,
                image = R.drawable.invalid_name2,
                title = "Взрослые"
            ),
            Category(
                id = 2,
                image = R.drawable.invalid_name3,
                title = "Пожилые"
            ),
            Category(
                id = 3,
                image = R.drawable.invalid_name4,
                title = "Животные"
            ),
            Category(
                id = 4,
                image = R.drawable.invalid_name5,
                title = "Мероприятия"
            )
        )
    }

}