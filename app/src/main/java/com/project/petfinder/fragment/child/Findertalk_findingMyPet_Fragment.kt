package com.project.petfinder.fragment.child

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.petfinder.R

class Findertalk_findingMyPet_Fragment : Fragment() {
    companion object {
        fun newInstance() : Findertalk_findingMyPet_Fragment = Findertalk_findingMyPet_Fragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.activity_findertalk_finding_my_pet, container, false)
    }

}