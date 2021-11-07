package com.project.petfinder.Nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.petfinder.*

import com.project.petfinder.fragment.CalendarFragment
import com.project.petfinder.fragment.HomeFragment

import com.project.petfinder.fragment.MyInfoFragment

/**
 * @Class : CommonNavActivity
 * @Description : 현재 Main화면으로 각 페이지의 부모역할 (네비게이션 중심으로 만들어진 클래스)
 * 어떤화면으로 넘어갈건지 셋팅해주는 클래스
 */
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

    // 내정보 - 비밀번호 변경
    private lateinit var  myInfoChangePwFragment: MyInfoChangePwFragment

    // 내정보 - 회원탈퇴
    private lateinit var  myInfoDeleteFragment: MyInfoDeleteFragment


    lateinit var uid : String
    lateinit var email : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_nav)

        var bottom_navigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        // SplashActivity 받은 uid를 지역변수로 할당
        uid = intent.getStringExtra("uid").toString()

        bottom_navigation.setOnNavigationItemSelectedListener(this)
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_fragments, homeFragment).commit()

    }

    /**
     * TODO(" 바뀔 가능성 있음 참고!!!!!!!!!")
     * onNavigationItemSelected - 네비 바텀을 구성하는 요소들 할당
     * @Description: 네비 바텀을 구성하는 요소들을 셋팅하고 그것을 눌럿을 경우 supportFragmentManager가 프레임 레이아웃을 해당 요소로 변경
     */
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

    /**
     * TODO(" 바뀔 가능성 있음 참고!!!!!!!!!")
     * 현재 클래스에서 버튼을 눌렸을 때 replace 될 항목을 구성 및 할당
     */
    fun change_to_Menu(str:String) {

        when (str) {
            "HOME_1" -> { // 홈 -> 펫핀더
                // add the fragement here
                finderTalkMainFragment = FinderTalkMainFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, finderTalkMainFragment).commit()
            }

            "MY_1" -> { // 반려동물 등록하기 페이지

                petInfoRegisterFragment = PetInfoRegisterFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, petInfoRegisterFragment).commit()
            }

            "MY_2" -> { // 반려동물 수정하기 페이지
                petInfoModifyFragment = PetInfoModifyFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, petInfoModifyFragment).commit()
            }

            "MY_5" -> { // 비밀번호 변경 페이지

                myInfoChangePwFragment = MyInfoChangePwFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, myInfoChangePwFragment).commit()
            }

            "MY_6" -> { // 회원 탈퇴 페이지

                myInfoDeleteFragment = MyInfoDeleteFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, myInfoDeleteFragment).commit()
            }

            "MY_7" -> { // 회원 탈퇴 페이지

                myInfoDeleteFragment = MyInfoDeleteFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, myInfoDeleteFragment).commit()
            }

            "MY_8" -> { // 내 정보 메인으로 이동 (확인/등록)

                mypageFragment = MyInfoFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragments, mypageFragment).commit()
            }

            "MY_9" -> { // 내 정보 메인으로 이동 (취소)

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
