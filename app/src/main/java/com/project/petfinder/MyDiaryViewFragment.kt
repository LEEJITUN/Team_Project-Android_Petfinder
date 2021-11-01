package com.project.petfinder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.petfinder.Nav.CommonNavActivity

class MyDiaryViewFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity

    companion object {
        fun newInstance(): MyDiaryViewFragment = MyDiaryViewFragment()
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
            inflater.inflate(R.layout.activity_my_diary_view, container, false) //반환형 : View }

        // 뒤로가기 버튼
//        var btn_Back = view?.findViewById(R.id.btn_Back) as Button
//
//        btn_Back.setOnClickListener (View.OnClickListener {
//            commonNavActivity.change_to_Menu(6)
//        })

        return view
    }
}