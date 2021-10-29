package com.project.petfinder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Findertalk_BoardMain_Activity :  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findertalk_findmypet_write)
        val view = findViewById<View>(R.id.main_recyclerview) as RecyclerView
        view.layoutManager = LinearLayoutManager(this)
//        view.adapter = Findertalk_MyRecyclerViewAdapter()
    }
    //아직 작성중인 페이지
}