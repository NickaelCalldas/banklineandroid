package com.example.bankline_android.data

import android.util.Log
import androidx.lifecycle.liveData
import com.example.bankline_android.data.remote.BanklineApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BanklineRepository {
    private val TAG = javaClass.simpleName

    private val restApi = Retrofit.Builder()
        .baseUrl("http://192.168.1.108:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BanklineApi::class.java)

    fun findBankStatement(accountHolderId:Int) = liveData{
        emit(State.Wait)
        try{
            emit(State.Sucess(restApi.findBankStatement(accountHolderId)))
        }catch (e:Exception){
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }

}