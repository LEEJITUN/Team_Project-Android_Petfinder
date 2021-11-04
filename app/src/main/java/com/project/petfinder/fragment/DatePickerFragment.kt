package com.project.petfinder.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.project.petfinder.Join_User_PetInfo_Activity
import java.util.*

class DatePickerFragment : DialogFragment(), OnDateSetListener {
    lateinit var join_User_PetInfo_Activity: Join_User_PetInfo_Activity

    companion object {
        fun newInstance() : DialogFragment = DialogFragment()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        join_User_PetInfo_Activity = context as Join_User_PetInfo_Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        /*
                Create a DatePickerDialog using Theme.LayoutInflater

                    DatePickerDialog(Context context, int theme, DatePickerDialog.OnDateSetListener listener,
                        int year, int monthOfYear, int dayOfMonth)
             */

        // DatePickerDialog THEME_DEVICE_DEFAULT_LIGHT
        val dpd = DatePickerDialog(
            join_User_PetInfo_Activity,
            AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, year, month, day
        )

//        // DatePickerDialog THEME_DEVICE_DEFAULT_DARK
//        val dpd2 = DatePickerDialog(
//            join_User_PetInfo_Activity,
//            AlertDialog.THEME_DEVICE_DEFAULT_DARK, this, year, month, day
//        )
//
//        // DatePickerDialog THEME_HOLO_LIGHT
//        val join_User_PetInfo_Activity = DatePickerDialog(
//            getActivity(),
//            AlertDialog.THEME_HOLO_LIGHT, this, year, month, day
//        )
//
//        // DatePickerDialog THEME_HOLO_DARK
//        val dpd4 = DatePickerDialog(
//            getActivity(),
//            AlertDialog.THEME_HOLO_DARK, this, year, month, day
//        )
//
//        // DatePickerDialog THEME_TRADITIONAL
//        val dpd5 = DatePickerDialog(
//            getActivity(),
//            AlertDialog.THEME_TRADITIONAL, this, year, month, day
//        )

        // Return the DatePickerDialog
        return dpd
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the chosen date
    }
}