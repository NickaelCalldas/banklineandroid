package com.example.bankline_android.data

sealed class State<out T>{
    data class Sucess<out R>(val data:R? = null):State<R?>()
    data class Error(val message: String? = null) : State<Nothing>()
    object Wait : State<Nothing>()
}
