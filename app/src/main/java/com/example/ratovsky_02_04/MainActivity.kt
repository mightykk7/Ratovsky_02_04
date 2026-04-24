package com.example.ratovsky_02_04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginEditText = findViewById(R.id.login)
        passwordEditText = findViewById(R.id.password)
        sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE)
    }

    fun registr(view: View) {
        val login = loginEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (login.isEmpty() || password.isEmpty()) {
            showAlert("Ошибка", "Заполните все поля")
            return
        }

        if (password.length != 8) {
            showAlert("Ошибка", "Пароль должен содержать ровно 8 символов")
            return
        }

        val savedLogin = sharedPref.getString("saved_login", null)
        val savedPassword = sharedPref.getString("saved_password", null)

        if (savedLogin == null || savedPassword == null) {
            sharedPref.edit().apply {
                putString("saved_login", login)
                putString("saved_password", password)
                apply()
            }
            startActivity(Intent(this, SecondActivity::class.java))
            finish()
        } else {
            if (login == savedLogin && password == savedPassword) {
                startActivity(Intent(this, SecondActivity::class.java))
                finish()
            } else {
                showAlert("Ошибка", "Неверный логин или пароль")
            }
        }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }

}