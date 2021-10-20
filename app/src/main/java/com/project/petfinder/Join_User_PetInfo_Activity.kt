package com.project.petfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton

class Join_User_PetInfo_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_user_pet_info)

        val btnRegist : Button = findViewById(R.id.btnRegist)

        // 회원가입 완료 페이지 이동 ( JoinCompliteActivity )
        btnRegist.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, JoinCompliteActivity::class.java)
            startActivity(intent)
        })

        val petinfo : View = findViewById(R.id.petinfo)
        onRadioButtonClicked(petinfo);
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