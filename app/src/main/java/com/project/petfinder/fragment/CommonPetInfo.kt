package com.project.petfinder.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.project.petfinder.Join_User_PetInfo_Activity
import com.project.petfinder.R
import java.util.*

class CommonPetInfo : Fragment(){

    lateinit var join_User_PetInfo_Activity: Join_User_PetInfo_Activity
    var calendar = Calendar.getInstance()
    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)
    private lateinit var dialogFragment: DialogFragment

    companion object {
        fun newInstance() : CommonPetInfo = CommonPetInfo()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        join_User_PetInfo_Activity = context as Join_User_PetInfo_Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        var view: View = inflater.inflate(R.layout.common_pet_info, container, false)
        var btn_calender = view?.findViewById(R.id.btn_calender) as Button
        var btn_pet_brith = view?.findViewById(R.id.btn_pet_brith) as TextView


        btn_calender.setOnClickListener  {
            join_User_PetInfo_Activity.openDialog()
        }

        return view

    }


}

