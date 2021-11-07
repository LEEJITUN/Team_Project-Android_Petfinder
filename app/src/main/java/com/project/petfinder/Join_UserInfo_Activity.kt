package com.project.petfinder


import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.petfinder.databinding.ActivityJoinUerInfoBinding
import java.util.regex.Pattern
import com.project.petfinder.firebaseuser.UserService
import com.project.petfinder.firebaseuser.UserModel

/**
 * @Class : Join_UserInfo_Activity
 * @Description : 회원가입 시의 기능들
 *
 */
class Join_UserInfo_Activity : AppCompatActivity() {

    // bindig
    private lateinit var binding : ActivityJoinUerInfoBinding

    // Message
    private val MSG1 :String = "이름을 입력해주세요"
    private val MSG2 :String = "이메일을 입력해주세요"
    private val MSG3 :String = "비밀번호를 입력해주세요"
    private val MSG4 :String = "비밀번호 재입력해주세요"
    private val MSG5 :String = "비밀번호를 똑같이 입력해주세요"
    private val MSG6 :String = "비밀번호를 8자리 이상으로 입력해주세요"
    private val MSG7 :String = "이메일 형식이 아닙니다"
    private val MSG8 :String = "비밀번호 형식을 지켜주세요."


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_uer_info)

        val receive_intent = intent

        binding = DataBindingUtil.setContentView(this,R.layout.activity_join_uer_info)

        binding.btnregist.setOnClickListener {

            // 변수 할당
            var inputName = binding.inputName.text.toString()
            var inputEmail = binding.inputEmail.text.toString()
            var inputPw = binding.inputPw.text.toString()
            var inputRePw = binding.inputRePw.text.toString()


            // TODO("나중에 따로 메소드로 빼기")
            /*************** 유효성 검사 ***************/
            if(NullCheck(inputName)) {
                Toast.makeText(this, MSG1, Toast.LENGTH_LONG).show()
             
                return@setOnClickListener
            }

            /** 이메일 **/
            if(NullCheck(inputEmail)) {
                Toast.makeText(this, MSG2, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
                var t1 = Toast.makeText(this, MSG7, Toast.LENGTH_SHORT)
                t1.show()
                return@setOnClickListener
            }

            /** 비밀번호 **/
            if(NullCheck(inputPw)) {
                Toast.makeText(this, MSG3, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            // 비밀번호가 8자 이상인지
            if (inputPw.length < 8) {
                Toast.makeText(this, MSG6, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            //비밀번호 유효성
            if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", inputRePw)) {
                Toast.makeText(this, MSG8, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            /** 비밀번호 재확인 **/
            if(NullCheck(inputRePw)) {
                Toast.makeText(this, MSG4, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            // 비밀번호 2개가 같은지 확인
            if(!inputPw.equals(inputRePw)) {
                Toast.makeText(this, MSG5, Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }
            /*********************************************/


            // 폰번호 인증 시 받은 핸드폰 번호를 phone변수에 할당
            val phone = receive_intent.getStringExtra("phone")


            /*************** FireBase Create userModel ***************/

            // usermodel 정보 할당
            var usermodel : UserModel = UserModel(
                inputEmail,
                inputPw,
                inputName,
                phone.toString(),
                "EMPTY"
            )

            // 유저 서비스 객체 생성
            var userService = UserService()

            // 파이어베이스에 추가
            userService.createFireBaseAuthUser(usermodel,this,Join_User_PetInfo_Activity::class.java)
            /*********************************************/


            /** 준영님 글쓰기 테스트 할 때 위에꺼 주석하고 이거 주석 풀고 ㄱㄱ!!**/
           // userService.createFireBaseAuthUser(usermodel,this, CommonNavActivity::class.java)


        }
    }

    /**
     * @Service : NullCheck - null이나 "" 공백 체크
     * @return : Blooean (빈값이면 : true | 빈값이 아니면: false)
     */
    fun NullCheck(inputData : String):Boolean {
        return inputData.isEmpty() || inputData.equals("")
    }



}