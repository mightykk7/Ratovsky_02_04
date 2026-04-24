package com.example.ratovsky_02_04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
    }
    fun toMain(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}