package com.project.petfinder.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.petfinder.R

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.activity_home, container, false) //반환형 : View }
    }

}

//class HomeFragment : Fragment() ,View.OnClickListener  {
//    private val fragmentA = Fragment()
//    var commonNavActivity: CommonNavActivity? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view: View = inflater.inflate(com.project.petfinder.R.layout.activity_findertalk_nav, container, false)
//
//        onClick(view)
//
//        return inflater.inflate(com.project.petfinder.R.layout.activity_main, container, false)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        commonNavActivity = context as CommonNavActivity
//    }
//
//    override fun onClick(view: View?) {
//
//        var button = view?.findViewById(com.project.petfinder.R.id.btnMore) as Button
//        // 프래그먼트 1에서 프래그먼트 2를 띄운다.
//        button.setOnClickListener(View.OnClickListener { commonNavActivity?.fragmentChange(1) })
//
//
//    }
//}