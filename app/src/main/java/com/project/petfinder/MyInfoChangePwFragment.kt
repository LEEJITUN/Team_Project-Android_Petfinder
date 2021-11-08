package com.project.petfinder

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.databinding.ActivityMyInfoChangePwBinding
import com.project.petfinder.utils.FBRef



class MyInfoChangePwFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity
    lateinit var binding:ActivityMyInfoChangePwBinding

    // Message 상수로 할당
    private val MSG1 :String = "현재 비밀번호를 입력해주세요"
    private val MSG2 :String = "변경 할 비밀번호를 입력해주세요"
    private val MSG3 :String = "재확인 비밀번호를 입력해주세요"
    private val MSG4 :String = "변결 할 비밀번호와 재확인할 비밀번호가 일치하지 않습니다."


    companion object {
        fun newInstance(): MyInfoChangePwFragment = MyInfoChangePwFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        commonNavActivity = getActivity() as CommonNavActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // binding 할당
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_my_info_change_pw, container, false)
        val view: View = binding.getRoot()

        //var view: View = inflater.inflate(R.layout.activity_my_info_change_pw, container, false) //반환형 : View }

        // 확인 버튼 클릭 시 -> 내정보 화면으로 이동
        //var btnSubmit = view?.findViewById(R.id.btnSubmit) as Button
        binding.btnSubmit.setOnClickListener(View.OnClickListener {

            modifyPasswordUser(commonNavActivity.uid,commonNavActivity.email)

        })

        // 취소 버튼 클릭 시 -> 내정보 화면으로 이동
        binding.btnCancel.setOnClickListener(View.OnClickListener {

            commonNavActivity.change_to_Menu("MY_9")

        })

        // 뒤로가기 버튼
//        var btn_Back = view?.findViewById(R.id.btn_Back) as Button
//
//        btn_Back.setOnClickListener (View.OnClickListener {
//            commonNavActivity.change_to_Menu(6)
//        })

        return view
    }

    /*** modifyPasswordUser(uid :String) -  (해당) User Password 수정
     *  Param1 : String (uid)
     ***/
    fun modifyPasswordUser(uid :String,email:String) {

        Log.d(ContentValues.TAG, "SERVICE - modifyUser")

        // TODO ("알림창 만들어야함 - "수정 하시겠습니까?" ")


        // email,password 값 할당
        val prePassword = binding.inputPw.text.toString()
        val newPassword = binding.inputRePw1.text.toString()
        val newPassword2 = binding.inputRePw2.text.toString()

        /*** 유효성 체크 ***/
        if(NullCheck(prePassword)) {
            Toast.makeText(commonNavActivity, MSG1, Toast.LENGTH_LONG).show()
            return
        }
        if(NullCheck(newPassword)) {
            Toast.makeText(commonNavActivity, MSG2, Toast.LENGTH_LONG).show()
            return
        }

        if(!newPassword.equals(newPassword2)) {
            Toast.makeText(commonNavActivity, MSG3, Toast.LENGTH_LONG).show()
            return
        }

        if(!newPassword.equals(newPassword2)) {
            Toast.makeText(commonNavActivity, MSG4, Toast.LENGTH_LONG).show()
            return
        }

        /*** FireBase에서 비밀번호 수정 ***/
        // firebase auth user create
        Firebase.auth.signInWithEmailAndPassword(email, prePassword).addOnCompleteListener(commonNavActivity) { task ->

            if (task.isSuccessful) {

                // firebase-auth에서 user객체 가져오기
                val user = task.getResult()?.user

                // updatePassword 메소드로 비밀번호 변경
                user!!.updatePassword(newPassword)
                Log.d(TAG, "User password updated.")
                
                // firebase-database에서 UserModel의 userPassword 수정
                FBRef.userInfoRef.child(uid).child("userPassword").setValue(binding.inputRePw1.text.toString())
                Toast.makeText(commonNavActivity, "비밀 번호 수정완료", Toast.LENGTH_LONG).show()

                // 내정보 페이지로 이동
                commonNavActivity.change_to_Menu("MY_8")
            } else {
                Toast.makeText(context, "비밀번호가 같지 않습니다.", Toast.LENGTH_LONG).show()
//                Toast.makeText(context, "실패", Toast.LENGTH_LONG).show()
            }
        }
        /******************************/

    }


    /**
     * @Service : NullCheck - null이나 "" 공백 체크
     * @return : Blooean (빈값이면 : true | 빈값이 아니면: false)
     */
    fun NullCheck(inputData : String):Boolean {
        return inputData.isEmpty() || inputData.equals("")
    }
}
