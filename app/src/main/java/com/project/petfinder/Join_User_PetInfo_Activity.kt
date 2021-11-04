package com.project.petfinder

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.project.petfinder.fragment.DatePickerFragment
import java.util.*

class Join_User_PetInfo_Activity : AppCompatActivity() {

    private lateinit var dialogFragment: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_user_pet_info)

        val btnRegist : Button = findViewById(R.id.btnRegist)


        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        // 회원가입 완료 페이지 이동 ( JoinCompliteActivity )
        btnRegist.setOnClickListener(View.OnClickListener {
            openDialog()

//            val intent = Intent(applicationContext, JoinCompliteActivity::class.java)
//            startActivity(intent)
        })

//        val petinfo : View = findViewById(R.id.petinfo)
//        onRadioButtonClicked(petinfo);


    }


    fun openDialog(){
        dialogFragment =  DatePickerFragment.newInstance()

//        dialogFragment.show(getSupportFragmentManager(),"Dialog")
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_no ->
                    if (checked) {
                        // Pirates are the best
                    }
                R.id.radio_male ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }
}