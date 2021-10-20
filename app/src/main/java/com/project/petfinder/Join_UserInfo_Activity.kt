package com.project.petfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Join_UserInfo_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_uer_info)

        val btnregist : Button = findViewById(R.id.btnregist)

        // 회원 팻 정보 입력 페이지 이동 ( Join_User_PetInfo_Activity )
        btnregist.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Join_User_PetInfo_Activity::class.java)
            startActivity(intent)
        })
    }
}