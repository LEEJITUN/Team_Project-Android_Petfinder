package com.project.petfinder


import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.widget.EditText
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    // Firebase auth 초기화
    private lateinit var auth: FirebaseAuth
    // databinding 초기화
    private lateinit var binding : ActivityLoginBinding

    private val MSG1 :String  = "이메일을 입력해주세요"
    private val MSG2 :String = "비밀번호를 입력해주세요"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 인증 할당
        auth = Firebase.auth
        // 바인딩 할당
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)


        //엔터키 
        binding.inputId.setOnKeyListener(View.OnKeyListener { v, keyCode, event -> if (keyCode == KEYCODE_ENTER) true else false })
        binding.inputPw.setOnKeyListener(View.OnKeyListener { v, keyCode, event -> if (keyCode == KEYCODE_ENTER) true else false })

        /*** 이메일으로 가입 화면 넘기기 ***/
        binding. btnJoin.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Join_PhoneNumber_Activity::class.java)

            startActivity(intent)
        })

        /*** Email Login ***/
        binding.btnLogin.setOnClickListener{

            // ID/PW 할당
            var id = binding.inputId.text.toString()
            var pw = binding.inputPw.text.toString()

            Log.d(TAG,"아이디:" + id+ "!")
            Log.d(TAG,"비밀번호:" + pw +"!")

            // 유효성 검사 (Null Check)
            if(id.isEmpty() || id.equals("")){
                Toast.makeText(this, MSG1, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            if(pw.isEmpty() || pw.equals("")){
                Toast.makeText(this, MSG2, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            // 파이어베이스(id,pw) -> 성공 or 실패
            EmailLogin(id,pw)

        }


        /*** Kakao Login  ***/
        val callback =  kakaoLoginCallBack()
        binding.btnKakaoLogin.setOnClickListener(View.OnClickListener {

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                // 카카오톡 계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

        })
    }

    /*** 사용자 정보 요청 메소드 ***/
    private fun updateKakaoLoginEvent() {

        UserApiClient.instance.me { user: User?, throwable: Throwable? ->

            // 받아 올 정보값 할당
            var name = user!!.kakaoAccount!!.profile!!.nickname
            var id = user.kakaoAccount!!.profile!!.thumbnailImageUrl
            var phone = user.kakaoAccount?.email
            var profile = user.kakaoAccount?.profile?.thumbnailImageUrl

            // 카카오 회원이 맞을 경우
            if (user != null) {
                Log.i(
                    TAG, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )


                // 넘어갈 페이지 or 정보 저장 선택해야함... ㅠ
//                Glide.with(profileImage).load(user.kakaoAccount!!.profile!!.thumbnailImageUrl)
//                    .circleCrop().into<Target<Drawable>>(profileImage)



            } else {
                Log.e(TAG, "사용자 정보 요청 실패")
            }
        }
    }

    /*** kakao Login CallBack ***/
    fun kakaoLoginCallBack(): (OAuthToken?, Throwable?) -> Unit {
        // 로그인 성공/실패
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)

                // 실패시
            } else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")

                // 로그인 성공 시 UI적 부분 수정될 메소드
                updateKakaoLoginEvent()
            }
        }
        return callback
    }

    /*** Email Login ***/
    fun EmailLogin(email:String,password:String) {

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                val intent = Intent(this, CommonNavActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

            } else {

                Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()

            }
        }

    }


}