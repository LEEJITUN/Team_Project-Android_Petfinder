package com.project.petfinder.Nav

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.petfinder.MyDiaryWriteFragment
import com.project.petfinder.PetInfoModifyFragment

import com.project.petfinder.PetInfoRegisterFragment
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


    // 내정보 - 반려동물 등록하기
    private lateinit var  petInfoRegisterFragment: PetInfoRegisterFragment

    // 내정보 - 반려동물 수정하기
    private lateinit var  petInfoModifyFragment: PetInfoModifyFragment

    // 내정보 - 다이어리 쓰기
    private lateinit var  myDiaryWriteFragment: MyDiaryWriteFragment




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

    fun change_to_Menu(str:String) {

        when (str) {
            "HOME_1" -> { // 홈 -> 펫핀더
                // add the fragement here
                finderTalkMainFragment = FinderTalkMainFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, finderTalkMainFragment).commit()
            }

            "MY_1" -> { // 내 정보 -> 등록하기 버튼 클릭

                petInfoRegisterFragment = PetInfoRegisterFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, petInfoRegisterFragment).commit()
            }

            "MY_2" -> { // 내 정보 -> 수정하기 이미지 버튼 클릭
                petInfoModifyFragment = PetInfoModifyFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, petInfoModifyFragment).commit()
            }

            "MY_3" -> { // 내 정보 - 다이어리 추가하기

                myDiaryWriteFragment = MyDiaryWriteFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, myDiaryWriteFragment).commit()
            }


//            20 -> { // ( 일단 보류 안보여서 )내정보 - 뒤로가기 버튼 클릭 -> 내정보 화면으로 이동 버튼 이름이 동일하면 같이 써도 되지 않을까?
//
//                mypageFragment = MyInfoFragment.newInstance()
//                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, mypageFragment).commit()
//            }

            "MY_4" -> { // 내 정보 - 등록 확인버튼 클릭 시 -> 내정보 화면으로 이동 (같이 쓰는 중)

                mypageFragment = MyInfoFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, mypageFragment).commit()
            }

            else -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, homeFragment).commit()
            }
        }


    }





}
