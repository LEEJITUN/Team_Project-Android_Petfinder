package com.project.petfinder.fragment


import android.app.DatePickerDialog
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
import com.project.petfinder.PetInfoModifyFragment
import com.project.petfinder.PetInfoRegisterFragment
import com.project.petfinder.R
import java.util.*

class CommonPetInfo  {

//    lateinit var join_User_PetInfo_Activity: Join_User_PetInfo_Activity
//    lateinit var petInfoModifyFragment: PetInfoModifyFragment
//    lateinit var petInfoRegisterFragment: PetInfoRegisterFragment
//
//    private lateinit var dialogFragment: DialogFragment
//
//    companion object {
//        fun newInstance(): CommonPetInfo = CommonPetInfo()
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        join_User_PetInfo_Activity = context as Join_User_PetInfo_Activity
//        petInfoModifyFragment = context as PetInfoModifyFragment
//        petInfoRegisterFragment = context as PetInfoRegisterFragment
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        var view: View = inflater.inflate(R.layout.common_pet_info, container, false)
//    }

    fun calendar(context: Context, view: View) {

        var calendar: Calendar = Calendar.getInstance()
        var year: Int = calendar.get(Calendar.YEAR)
        var month: Int = calendar.get(Calendar.MONTH)
        var day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        var btn_calender = view?.findViewById(R.id.btn_calender) as Button
        var tv_pet_brith = view?.findViewById(R.id.tv_pet_brith) as TextView

        btn_calender.setOnClickListener(View.OnClickListener {
            val Cal = Calendar.getInstance()
            day = Cal[Calendar.DATE]
            month = Cal[Calendar.MONDAY]
            year = Cal[Calendar.YEAR]
            val datePickerDialog = DatePickerDialog(
                context, android.R.style.Theme_DeviceDefault_Dialog,
                { view, year, month, dayOfMonth ->
                    tv_pet_brith.setText( "$year-$month-$dayOfMonth")
                },
                year, month + 1, day
            )
            datePickerDialog.show()
        })
    }
}

