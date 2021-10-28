package com.project.petfinder.Nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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




//class CommonNavActivity : AppCompatActivity() {
//        private lateinit var context: Context
//
//        var frameLayout1:FinderTalkNavActivity? = null
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(com.project.petfinder.R.layout.activity_common_nav)
//
//            val adapter = PagerAdapter(supportFragmentManager)
//            adapter.addFragment(MainFragment(), "메인")
//            adapter.addFragment(CalendarFragment(), "캘린더")
//            adapter.addFragment(FinderTalkNavActivity(), "핀더톡")
//            adapter.addFragment(MyInfoFragment(), "내정보")
//
//            val viewpager = findViewById<androidx.viewpager.widget.ViewPager>(com.project.petfinder.R.id.viewpager)
//            viewpager.adapter = adapter
//            val tablayout = findViewById<com.google.android.material.tabs.TabLayout>(com.project.petfinder.R.id.tablayout)
//            tablayout.setupWithViewPager(viewpager)
//        }
//
//    // fragmentA 에서 frameLayoutB에 fragment 추가하기 위해 호출 하는 메서드
////    fun openFragmentOnFrameLayout(int: Int){
////        val transaction = supportFragmentManager.beginTransaction()
////        when(int){
////            1 -> transaction.replace(R.id.viewpager, FinderTalkNavActivity())
////        }
////        transaction.commit()
////    }
//
//    fun fragmentChange(index: Int) {
//        if (index == 1) {
//            frameLayout1?.let {
//                supportFragmentManager.beginTransaction()
//                    .replace(com.project.petfinder.R.id.viewpager, it).commit()
//            }
//        }
//    }



}
