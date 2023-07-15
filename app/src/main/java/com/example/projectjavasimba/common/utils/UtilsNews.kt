package com.example.projectjavasimba.common.utils

import com.example.projectjavasimba.data.entity.EventEntity

sealed class UtilsNews {
    object Loader : UtilsNews()
    class Finish(val data:List<EventEntity>): UtilsNews()
}