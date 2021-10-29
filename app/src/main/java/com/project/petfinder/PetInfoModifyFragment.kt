package com.project.petfinder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.project.petfinder.Nav.CommonNavActivity

class PetInfoModifyFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity

    companion object {
        fun newInstance(): PetInfoModifyFragment = PetInfoModifyFragment()
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
            inflater.inflate(R.layout.activity_pet_info_modify, container, false) //반환형 : View }

        // 등록하기 버튼
        var btnSubmit = view?.findViewById(R.id.btnSubmit) as Button

        btnSubmit.setOnClickListener(View.OnClickListener {

            commonNavActivity.change_to_Menu("MY_4")

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