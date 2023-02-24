package com.example.projectjavasimba.utils

import com.example.projectjavasimba.data.entity.Event

sealed class UtilsNews {
    object Loader : UtilsNews()
    class Finish(val data:List<Event>):UtilsNews()
}