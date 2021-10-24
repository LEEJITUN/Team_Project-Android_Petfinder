package com.project.petfinder

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Findertalk_mypetpic_write_Activity : AppCompatActivity(){
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findertalk_mypetpic_write)
        title = "스피너 테스트"

        var Boardtitle = arrayOf("임보/입양", "커뮤니티")

        var MypetPic = arrayOf("임시보호","입양","우리 아이 자랑하기")

        var spinner = findViewById<Spinner>(R.id.spinner)
        var spinner2 = findViewById<Spinner>(R.id.spinner2)


        var adapter: ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Boardtitle)
        spinner.adapter = adapter

        var adapter2: ArrayAdapter<String>
        adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, MypetPic)
        spinner2.adapter = adapter2
    }
}