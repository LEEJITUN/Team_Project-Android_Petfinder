package com.project.petfinder.Nav

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.google.android.material.tabs.TabLayout

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.project.petfinder.Adapter.FinderTalkViewPagerAdapter
import com.project.petfinder.R

//TODO: To be deleted by junyeong
class FinderTalkMainFragment : Fragment(){
//    private lateinit var tabLayout: TabLayout
//    private lateinit var viewPager: ViewPager2
//
//    companion object {
//        fun newInstance() : FinderTalkMainFragment = FinderTalkMainFragment()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
//
//        // 해당 뷰 (activity_findertalk_nav) - 핀더톡 메인 네비
//        var view: View = inflater.inflate(R.layout.activity_findertalk, container, false)
//
//        tabLayout = view.findViewById(R.id.tab_layout)
//        viewPager = view.findViewById(R.id.findertalk_view_pager)//원래 이거 findertalk_view_pager
//
//        val adapter = FinderTalkViewPagerAdapter(this)
//        viewPager.adapter = adapter
//
//        val tabName = arrayOf<String>("커뮤니티", "가족 찾는중", "임보|입양")
//
//        //슬라이드로 이동했을 때, 탭이 같이 변경되도록
//        TabLayoutMediator(tabLayout, viewPager) {
//                tab, position -> tab.text = tabName[position].toString()
//        }.attach()
//
//        //탭이 선택되었을 때, 뷰페이저가 같이 변경되도록
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//
//            override fun onTabSelected(tab: TabLayout.Tab?) { viewPager.currentItem = tab!!.position }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) { }
//            override fun onTabReselected(tab: TabLayout.Tab?) { }
//        })
//
//        return view
//    }
}

