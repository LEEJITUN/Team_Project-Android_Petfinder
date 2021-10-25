package com.project.petfinder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class Join_PhoneNumber_Activity : AppCompatActivity() {
    // 인증 번호
    var checkNum : String  ?= null
    val SMS_RECEIVE_PERMISSION :Int = 1

    lateinit var inputPhoneNum  : EditText
    lateinit var sendSMSBt  : Button
    lateinit var inputCheckNum: EditText
    lateinit var checkBt  : Button

    // 쉐어드
    lateinit  var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_phone_number)

        /* 변수 정의 */
        inputPhoneNum  = findViewById(R.id.input_phone_num)
        sendSMSBt  = findViewById(R.id.send_sms_button)

        inputCheckNum  = findViewById(R.id.input_check_num)
        checkBt  = findViewById(R.id.check_button)

        // 인증번호를 비교하기 위해 쉐어드에 저장
        pref = getPreferences(Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = pref.edit()


        /* 문자 보내기 권한 확인 */
        var permissionCheck :Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)

        if(permissionCheck != PackageManager.PERMISSION_GRANTED){

            // 문자보내기 권한 거부
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.SEND_SMS)){
                Toast.makeText(getApplication(),"SMS 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }

            // 문자 보내기 권한 허용
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), SMS_RECEIVE_PERMISSION)
        }

        /* sms보내기 버튼 이벤트 */
        sendSMSBt.setOnClickListener(View.OnClickListener {
            checkNum = numberGen(4,1)

            // 쉐어드에 생성한 인증번호 저장
            editor.putString("checkNum",checkNum)
            // apply()를 해줘야 저장된다..
            editor.apply()

            // sms 보내기
            sendSMS(inputPhoneNum.getText().toString(),"인증번호"+checkNum)


            // 인증 번호 체크 부분 활성화
            inputCheckNum.setVisibility(View.VISIBLE);
            checkBt.setVisibility(View.VISIBLE);

            // 공기계로 테스트 할 거기때문에 문자는 가지 않고 토스트로 확인하기 위해 해둔것.
            Toast.makeText(getApplicationContext(), checkNum, Toast.LENGTH_SHORT).show()

        })

        /* 인증번호 체크하는 버튼 이벤트 */
        checkBt.setOnClickListener(View.OnClickListener {

            // 입력한 인증번호화 저장된 인증번호 체크
            if(pref.getString("checkNum","").equals(inputCheckNum.getText().toString())){
                Toast.makeText(getApplicationContext(), "인증이 완료 되었습니다.", Toast.LENGTH_SHORT).show()

                // 회원 정보 입력 화면 이동
                val intent = Intent(applicationContext, Join_UserInfo_Activity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(getApplicationContext(), "인증번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }

        })
    }

    /* SMS 보내기 */
    fun sendSMS(phoneNumber: String, message: String) {

        val sms = SmsManager.getDefault()
        // SMS 메세지 설정
        sms.sendTextMessage(phoneNumber, null, message,null, null)

        Toast.makeText(getBaseContext(), "메세지가 전송 되었습니다.", Toast.LENGTH_SHORT).show()

    }

    // 인증 번호 생성
    fun numberGen(len : Int, dupcd : Int) : String? {
        var rand = Random()
        // 난수가 저장될 변수
        var numStr: String? = ""

        var i = 0
        while (i < len) {

            // 0~9 까지 난수생성
            val ran = Integer.toString(rand.nextInt(10))

            // 중복 허용시 numStr 추가
            if (dupcd === 1) {
                numStr += ran
            } else if (numStr != null) {
                //중복을 허용하지 않을 시 중복된 값이 있는지 검사
                if (!numStr.contains(ran)) {
                    numStr === ran
                } else {
                    //생성된 난수가 중복되면 루틴을 다시 실행
                    i -= 1
                }
            }
            i++
        }

        return numStr
    }
}