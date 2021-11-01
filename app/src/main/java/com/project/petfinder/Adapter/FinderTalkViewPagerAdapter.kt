package com.project.petfinder.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.petfinder.fragment.child.Findertalk__AdoptAndForstering_Fragment

private const val NUM_PAGES = 3

class FinderTalkViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Findertalk__AdoptAndForstering_Fragment()
//            1 -> Findertalk_community_Fragment()
//            2 -> Findertalk_findingMyPet_Fragment()
            else -> Findertalk__AdoptAndForstering_Fragment()
        }
    }
}