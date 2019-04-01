package com.example.univerapp

import android.app.Application
import com.example.univerapp.core.Service
import com.example.univerapp.core.util.Constants.api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    companion object {
        lateinit var service: Service
    }

    private lateinit var retrofit: Retrofit

    override fun onCreate() {
//        FirebaseApp.initializeApp(applicationContext)
        super.onCreate()
        retrofit = Retrofit.Builder()
            .baseUrl(api)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(Service::class.java)
    }
}