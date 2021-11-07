package com.project.petfinder


import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.content.Intent
import android.util.Log
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
import com.project.petfinder.firebaseuser.UserModel
import com.project.petfinder.firebaseuser.UserService
import kotlinx.coroutines.*

/**
 * @Class : LoginActivity
 * @Description : Login 화면의 기능들의 클래스임.
 */
class LoginActivity : AppCompatActivity() {

    // Firebase auth 초기화
    private lateinit var auth: FirebaseAuth
    // databinding 초기화
    private lateinit var binding : ActivityLoginBinding

    // Message 상수로 할당
    private val MSG1 :String  = "이메일을 입력해주세요"
    private val MSG2 :String = "비밀번호를 입력해주세요"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 인증 할당
        auth = Firebase.auth
        // 바인딩 할당
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        //엔터키 안먹히게
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


        /*** Kakao Login ***/
        val callback =  kakaoLoginCallBack()
        binding.btnKakaoLogin.setOnClickListener(View.OnClickListener {

            // isKaKAoTalkLoginAvailable : 카카오 로그인이 가능한지에 대한 메소드
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                // 카카오톡 계정으로 로그인 (브라우저기반)
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        })

    }


    /**
     * @Service : KakaoLoginSuccessNextFireBase - Kakao 사용자 정보 요청 후 파이어베이스에 저장하는 메소드
     * @return
     * @Description : kakao 회원이 맞을 경우 회원정보를 받아서 Firebase users객체에 할당 시킴
     * 패스워드 같은경우는 존재하지 않기 때문에 "KakaoPw"라고 임의로 값 할당.. ㅠ
     */
    private fun KakaoLoginSuccessNextFireBase() {


        UserApiClient.instance.me { user: User?, throwable: Throwable? ->

            // 받아 올 정보값 할당
            var name = user!!.kakaoAccount!!.profile!!.nickname.toString()
            var email = user.kakaoAccount?.email.toString()
            var phone = user.kakaoAccount?.phoneNumber.toString()
            var profile = user.kakaoAccount?.profile?.thumbnailImageUrl.toString()

            // 카카오 회원이 맞을 경우
            if (user != null) {
                Log.i(
                    TAG, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )


                /** Kakao의 정보를 FireBase에 저장 **/

                // usermodel에 kakao회원정보 값 할당
                var usermodel : UserModel = UserModel(
                    email,
                    "KakaoPw",
                    name,
                    phone,
                    profile
                )

                // 유저 서비스 객체 생성
                var userService = UserService()
                // 위에서 준 해당 usermodel을 만들어달라고 보냄
                userService.createFireBaseAuthUser(usermodel,this,Join_User_PetInfo_Activity::class.java)
                /*******************************/

            } else {
                Log.e(TAG, "사용자 정보 요청 실패")
            }
        }
    }


    /**
     * @Service :  kakao Login CallBack - 로그인 성공,실패 callBack
     * @return : callback
     * @Description : 카카오로그인 성공,실패 둘다 콜백값을 보내고 
     * 로그인 성공시에 파이어베이스에 Users객체에 저장
     */
    fun kakaoLoginCallBack(): (OAuthToken?, Throwable?) -> Unit {
        // 로그인 성공/실패
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)


            } else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")

                // 로그인 성공 시 파이어베이스에 Users객체에 저장
                KakaoLoginSuccessNextFireBase()
            }
        }
        return callback
    }


    /**
     * @Service :  EmailLogin - 이메일로 로그인
     * @Param1: String (Email 주소)
     * @Param2: String (password)
     * @return
     * @Description : 파이어베이스에서 로그인하기 위해서는 FirebaseAuth에 있는 signInWithEmailAndPassword라는 메소드를 사용함.
     * 로그인 성공 -> 메인 | 로그인 실팰 -> 현재화면
     */
    fun EmailLogin(email:String,password:String) {

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                // 메인으로 이동
                val intent = Intent(this, CommonNavActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("uid",auth.uid)
                
                // 위에꺼아니면 밑에꺼임  테스트할때 한번 해보고 안되면 이거 주석 풀고 테스트
                //intent.putExtra("uid",auth.currentUser?.uid)
                startActivity(intent)

                Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

            } else {

                Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()

            }
        }

    }
}