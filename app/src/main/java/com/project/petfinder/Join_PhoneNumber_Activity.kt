package com.project.petfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Join_PhoneNumber_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_phone_number)

        val btnRevercode : Button = findViewById(R.id.btnRevercode)

        // 회원정보 입력 페이지 이동
        btnRevercode.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Join_UserInfo_Activity::class.java)
            startActivity(intent)
        })
    }
}