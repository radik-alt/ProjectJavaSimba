package com.example.common

fun <T> List<T>?.toArrayList(): ArrayList<T> {
    return if (this != null) kotlin.collections.ArrayList(this) else arrayListOf()
}