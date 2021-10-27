package com.project.petfinder.Nav

import android.os.Bundle
import androidx.fragment.app.Fragment

import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import com.project.petfinder.Adapter.PagerAdapter
import com.project.petfinder.R
import com.project.petfinder.fragment.child.Findertalk_adoptandfosteringDetailsFragment
import com.project.petfinder.fragment.child.Findertalk_community_Fragment
import com.project.petfinder.fragment.child.Findertalk_findingMyPet_Fragment


class FinderTalkNavActivity : Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_findertalk_nav, container, false)
        // Setting ViewPager for each Tabs
        val viewPager = view.findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager)
        // Set Tabs inside Toolbar
        val tabs = view.findViewById(R.id.tablayout) as TabLayout
        tabs.setupWithViewPager(viewPager)
        return view
    }


    // Add Fragments to Tabs
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = PagerAdapter(childFragmentManager)
        adapter.addFragment(Findertalk_adoptandfosteringDetailsFragment(), "임보/입양")
        adapter.addFragment(Findertalk_community_Fragment(), "커뮤니티")
        adapter.addFragment(Findertalk_findingMyPet_Fragment(), "가족 찾는 중")
        viewPager.adapter = adapter
    }

}

