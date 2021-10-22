package com.project.petfinder

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.petfinder.fragment.CalendarFragment
import com.project.petfinder.fragment.MainFragment
import com.project.petfinder.fragment.MyInfoFragment


class CommonNavActivity : AppCompatActivity() {
        private lateinit var context: Context

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_common_nav)


            val adapter = PagerAdapter(supportFragmentManager)
            adapter.addFragment(MainFragment(), "메인")
            adapter.addFragment(CalendarFragment(), "캘린더")
//          adapter.addFragment(MyPageFragment(), "핀더톡")
            adapter.addFragment(MyInfoFragment(), "내정보")
            
            val viewpager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewpager)
            viewpager.adapter = adapter
            val tablayout = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tablayout)
            tablayout.setupWithViewPager(viewpager)
        }

    }
