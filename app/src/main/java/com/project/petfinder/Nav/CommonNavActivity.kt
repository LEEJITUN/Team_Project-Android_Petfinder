package com.project.petfinder.Nav

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.petfinder.R
import com.project.petfinder.fragment.CalendarFragment
import com.project.petfinder.fragment.HomeFragment

import com.project.petfinder.fragment.MyInfoFragment


class CommonNavActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {

    //멤버변수
    private lateinit var homeFragment: HomeFragment
    private lateinit var calendarFragment: CalendarFragment
    private lateinit var finderTalkMainFragment: FinderTalkMainFragment
    private lateinit var mypageFragment: MyInfoFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_nav)

        var bottom_navigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener(this)
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_fragments, homeFragment).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.action_home ->{
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, homeFragment).commit()
            }
            R.id.action_calender -> {
                calendarFragment = CalendarFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, calendarFragment).commit()
            }
            R.id.action_fimderTalk -> {
                finderTalkMainFragment = FinderTalkMainFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, finderTalkMainFragment).commit()
            }

            R.id.action_mypage -> {
                mypageFragment = MyInfoFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, mypageFragment).commit()
            }
        }
        return true

    }

    fun change_to_Menu(index:Int) {

        when (index) {
            1 -> { // 펫핀더
                // add the fragement here
                finderTalkMainFragment = FinderTalkMainFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, finderTalkMainFragment).commit()
            }
            2 -> { // 내정보

            }
            else -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, homeFragment).commit()
            }
        }


    }





}
