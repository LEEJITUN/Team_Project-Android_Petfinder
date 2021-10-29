package com.project.petfinder.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.Nav.FinderTalkMainFragment
import com.project.petfinder.R


class HomeFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        commonNavActivity = getActivity() as CommonNavActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        var view: View = inflater.inflate(R.layout.activity_home, container, false)

        var btnMore = view?.findViewById(R.id.btnMore) as Button

        // 프래그먼트 1에서 프래그먼트 2를 띄운다.
        btnMore.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("HOME_1")
        })
        return view

    }

}
