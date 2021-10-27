package com.project.petfinder.Adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {
    
    // 프레그먼트
    private val fragmentList = ArrayList<Fragment>()
    // 탭 텍스트
    private val titleList = ArrayList<String>()

    // 프레그먼트 수
    override fun getItem(position: Int): Fragment = fragmentList[position]

    // 타이틀 수
    override fun getCount(): Int = titleList.size

    override fun getPageTitle(position: Int): CharSequence = titleList[position]

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

}