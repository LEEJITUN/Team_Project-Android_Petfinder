package com.project.petfinder.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.R
import com.project.petfinder.banner.MyIntroPagerRecyclerAdapter
import com.project.petfinder.banner.PageItem


class HomeFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity

    // 배너 버튼 초기화
    lateinit var previous_btn: ImageView
    lateinit var next_btn: ImageView
    lateinit var my_intro_view_pager:ViewPager2
    lateinit var dots_indicator:com.tbuonomo.viewpagerdotsindicator.DotsIndicator

    // 배너 어댑터 초기화
    private lateinit var bannerRecyclerAdapter: MyIntroPagerRecyclerAdapter

    private var pageItemList = ArrayList<PageItem>()

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
        // 배너
        banner(view)

        return view

    }

    // 배너
    fun banner(view:View){

        // 배너 UI 셋팅
        previous_btn = view?.findViewById(R.id.previous_btn)
        next_btn = view?.findViewById(R.id.next_btn)
        my_intro_view_pager = view?.findViewById(R.id.my_intro_view_pager)
        dots_indicator = view?.findViewById(R.id.dots_indicator)


        // Pre Button
        previous_btn.setOnClickListener {
            Log.d(TAG, "MainActivity - 이전 버튼 클릭")

            my_intro_view_pager.currentItem = my_intro_view_pager.currentItem - 1
        }
        // Next Button
        next_btn.setOnClickListener {
            Log.d(TAG, "MainActivity - 다음 버튼 클릭")
            my_intro_view_pager.currentItem = my_intro_view_pager.currentItem + 1
        }

        // 배너 사진 설정
        pageItemList.add(PageItem(R.drawable.banner1))
        pageItemList.add(PageItem(R.drawable.banner2))
        pageItemList.add(PageItem(R.drawable.banner3,))
        pageItemList.add(PageItem(R.drawable.banner4))



        bannerRecyclerAdapter = MyIntroPagerRecyclerAdapter(pageItemList)

        // 뷰페이저에 설정
        my_intro_view_pager.apply {
            adapter = bannerRecyclerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            dots_indicator.setViewPager2(this)
        }

//        my_intro_view_pager?.setAdapter(bannerRecyclerAdapter) // 리사이클러뷰에 어댑터 연결
    }


}



