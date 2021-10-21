package com.project.petfinder

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Findertalk_mypetpic_write : AppCompatActivity(){
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findertalk_mypetpic_write)
        title = "스피너 테스트"

        var Boardtitle = arrayOf("Pet 톡톡", "짱구는 못말려",
            "아저씨", "아바타", "대부", "국가대표", "토이스토리3",
            "마당을 나온 암탉", "죽은 시인의 사회", "서유기")

        var MypetPic = arrayOf("우리 아이 자랑하기!", "짱구는 못말려",
            "아저씨", "아바타", "대부", "국가대표", "토이스토리3",
            "마당을 나온 암탉", "죽은 시인의 사회", "서유기")

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