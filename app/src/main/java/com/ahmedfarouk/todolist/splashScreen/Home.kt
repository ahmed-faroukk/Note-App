package com.ahmedfarouk.todolist.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import com.ahmedfarouk.todolist.MainActivity
import com.ahmedfarouk.todolist.R

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            this.finish()
        } , 2000)

    }
}