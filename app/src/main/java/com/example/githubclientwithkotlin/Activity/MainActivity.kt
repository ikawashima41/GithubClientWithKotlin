package com.example.githubclientwithkotlin.Activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.githubclientwithkotlin.API.APIClient
import com.example.githubclientwithkotlin.R
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()

        thread {
            try {
                val resonse = APIClient().fetchRepository().execute()
                val body = resonse.body().toString()
                Log.d("retrofit", body.toString())
                handler.post(Runnable {
                })
            } catch (e: Exception) {
                Log.w("retrofit", "fetchRepository")
            }
        }
    }
}
