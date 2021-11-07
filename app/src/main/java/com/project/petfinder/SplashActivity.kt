package com.project.petfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.petfinder.Nav.CommonNavActivity

/**
 * @Class : SplashActivity Class
 * @Description : 어플이 시작될때 3초간 현재 유저가 로그인 했던 사용자 인가 아닌가를 판가름 하는 클래스
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Firebase 인증(사용자)
        auth = Firebase.auth

        // 현재 로그인한 사용자가 아니라면 -> LoginActivity(로그인 화면)
        Handler().postDelayed({
            if(auth.currentUser?.uid == null) {
                Log.d("SplashActivity", "null")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

            } else { // 현재 로그인한 사용자이면 -> CommonNavActivity (메인 홈 화면)
                Log.d("SplashActivity", "not null")

                // 현재 로그인한 사용자가 맞다면 메인에 uid를 담아줌
                val intent = Intent(this, CommonNavActivity::class.java)
                intent.putExtra("uid",auth.currentUser?.uid)
                startActivity(intent)
                finish()

            }
        }, 3000)


    }
}