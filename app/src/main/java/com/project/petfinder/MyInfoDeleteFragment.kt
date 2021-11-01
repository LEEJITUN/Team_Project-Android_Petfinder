package com.project.petfinder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.project.petfinder.Nav.CommonNavActivity

class MyInfoDeleteFragment : Fragment() {
    lateinit var commonNavActivity: CommonNavActivity

    companion object {
        fun newInstance(): MyInfoDeleteFragment = MyInfoDeleteFragment()
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

        var view: View =
            inflater.inflate(R.layout.activity_my_info_delete, container, false) //반환형 : View }

        // 확인 버튼 클릭 시 -> 내정보 화면으로 이동
        var btnSubmit = view?.findViewById(R.id.btnSubmit) as Button

        btnSubmit.setOnClickListener(View.OnClickListener {

            commonNavActivity.change_to_Menu("MY_8")

        })

        // 취소 버튼 클릭 시 -> 내정보 화면으로 이동
        var btnCancel = view?.findViewById(R.id.btnCancel) as Button

        btnCancel.setOnClickListener(View.OnClickListener {

            commonNavActivity.change_to_Menu("MY_9")

        })

        // 뒤로가기 버튼
//        var btn_Back = view?.findViewById(R.id.btn_Back) as Button
//
//        btn_Back.setOnClickListener (View.OnClickListener {
//            commonNavActivity.change_to_Menu(20)
//        })
        return view
    }
}