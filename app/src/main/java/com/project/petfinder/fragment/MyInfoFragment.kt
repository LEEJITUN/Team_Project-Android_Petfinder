package com.project.petfinder.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.project.petfinder.LoginActivity
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.PetInfoRegisterFragment
import com.project.petfinder.R

class MyInfoFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity

    companion object {
        fun newInstance(): MyInfoFragment = MyInfoFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        commonNavActivity = getActivity() as CommonNavActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflater the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_my_info, container, false)

        // 반려동물 등록하기 버튼
        var btnRegist = view?.findViewById(R.id.btnRegist) as Button

        btnRegist.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_1")
        })

        // 반려동물 수정하기 이미지 버튼
        var btnModify = view?.findViewById(R.id.btnModify) as ImageButton

        btnModify.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_2")
        })

        // 다이어리 추가하기 버튼
        var btnDiary = view?.findViewById(R.id.btnDiary) as Button

        btnDiary.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_3")
        })

        // 다이어리 보기 버튼
        var btnDiaryView = view?.findViewById(R.id.btnDiaryView) as Button

        btnDiaryView.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_4")
        })

        // 비밀번호 변경 버튼
        var btnPwchange = view?.findViewById(R.id.btnPwchange) as Button

        btnPwchange.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_5")
        })

        // 회원 탈퇴 버튼
        var btnWithdrawal = view?.findViewById(R.id.btnWithdrawal) as Button

        btnWithdrawal.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_6")
        })

        // 로그아웃 버튼
//        var btnLogout = view?.findViewById(R.id.btnLogout) as Button
//
//        btnWithdrawal.setOnClickListener(View.OnClickListener {
//            commonNavActivity.change_to_Menu("MY_7")
//        })

        return view
    }
}

