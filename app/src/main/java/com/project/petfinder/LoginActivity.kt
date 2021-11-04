package com.project.petfinder

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.widget.EditText
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
//import com.kakao.sdk.auth.model.OAuthToken
//import com.kakao.sdk.common.KakaoSdk
//import com.kakao.sdk.user.UserApiClient
//import com.kakao.sdk.user.model.User
//import com.nhn.android.naverlogin.OAuthLogin
//import com.nhn.android.naverlogin.OAuthLoginHandler
import com.project.petfinder.Nav.CommonNavActivity


class LoginActivity : AppCompatActivity() {


//    lateinit var mOAuthLoginInstance : OAuthLogin
//    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Kakao SDK 초기화
//        KakaoSdk.init(this, "12d9a1fd1f3a5b9027bf344de0a37896")

        val inputId: EditText = findViewById(R.id.inputId)
        val inputPw: EditText = findViewById(R.id.inputPw)
        val btnJoin: TextView = findViewById(R.id.btnJoin)
        val btnLogin: TextView = findViewById(R.id.btnLogin)
        val btnKakaoLogin: Button = findViewById(R.id.btnKakaoLogin)
//        val btnNaverLogin: com.nhn.android.naverlogin.ui.view.OAuthLoginButton = findViewById(R.id.btnNaverLogin)
//
//
//        //  네이버 아이디로 로그인
//        val naver_client_id = ""
//        val naver_client_secret = ""
//        val naver_client_name = ""
//
//        mContext = this
//
//        mOAuthLoginInstance = OAuthLogin.getInstance()
//        mOAuthLoginInstance.init(mContext, naver_client_id, naver_client_secret, naver_client_name)


        // 핸들러
//        @SuppressLint("HandlerLeak")
//        val mOAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
//            override fun run(success: Boolean) {
//                if (success) {
////                val accessToken: String = mOAuthLoginModule.getAccessToken(baseContext)
////                val refreshToken: String = mOAuthLoginModule.getRefreshToken(baseContext)
////                val expiresAt: Long = mOAuthLoginModule.getExpiresAt(baseContext)
////                val tokenType: String = mOAuthLoginModule.getTokenType(baseContext)
////                var intent = Intent(this, )
//                } else {
//                    val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
//                    val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)
//
//                    Toast.makeText(
//                        baseContext, "errorCode:" + errorCode
//                                + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//
//        btnNaverLogin.setOAuthLoginHandler(mOAuthLoginHandler)


        //엔터키 
        inputId.setOnKeyListener(View.OnKeyListener { v, keyCode, event -> if (keyCode == KEYCODE_ENTER) true else false })
        inputPw.setOnKeyListener(View.OnKeyListener { v, keyCode, event -> if (keyCode == KEYCODE_ENTER) true else false })

        // 이메일으로 가입 화면 넘기기
        btnJoin.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, Join_PhoneNumber_Activity::class.java)
            startActivity(intent)
        })

        // 로그인 시 메인화면 넘기기
        btnLogin.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, CommonNavActivity::class.java)
            startActivity(intent)
        })


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

        // 카카오 로그인 버튼
        btnKakaoLogin.setOnClickListener(View.OnClickListener {

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                // 카카오톡 계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

        })


        // Naver 로그인 버튼


    }

    // 사용자 정보 요청 메소드
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




}