package com.project.petfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.TextView


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inputId : EditText = findViewById(R.id.inputId)
        val inputPw : EditText = findViewById(R.id.inputPw)
        val btnJoin : TextView = findViewById(R.id.btnJoin)

        //엔터키 
        inputId.setOnKeyListener(View.OnKeyListener { v, keyCode, event -> if (keyCode == KEYCODE_ENTER) true else false })
        inputPw.setOnKeyListener(View.OnKeyListener { v, keyCode, event -> if (keyCode == KEYCODE_ENTER) true else false })

        // 이메일으로 가입 화면 넘기기
        btnJoin.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Join_PhoneNumber_Activity::class.java)
            startActivity(intent)
        })

    }
}