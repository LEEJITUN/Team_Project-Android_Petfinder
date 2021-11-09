package com.project.petfinder

import android.app.AlertDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.utils.FBAuth
import com.project.petfinder.utils.FBRef
import android.widget.Toast

import android.content.DialogInterface
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.project.petfinder.databinding.ActivityMyInfoChangePwBinding
import com.project.petfinder.databinding.ActivityMyInfoDeleteBinding
import com.project.petfinder.firebaseuser.UserModel


class MyInfoDeleteFragment : Fragment() {
    lateinit var commonNavActivity: CommonNavActivity
    lateinit var binding: ActivityMyInfoDeleteBinding

    companion object {
        fun newInstance(): MyInfoDeleteFragment = MyInfoDeleteFragment()
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
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_my_info_delete, container, false)
        val view: View = binding.getRoot()

        // 확인 버튼 클릭 시 -> 내정보 화면으로 이동
        binding.btnSubmit.setOnClickListener(View.OnClickListener {

            deleteUser(commonNavActivity.uid,view)
            commonNavActivity.change_to_Menu("MY_8")

        })

        // 취소 버튼 클릭 시 -> 내정보 화면으로 이동
        binding. btnCancel.setOnClickListener(View.OnClickListener {

            commonNavActivity.change_to_Menu("MY_9")

        })

        // 뒤로가기 버튼
//        var btn_Back = view?.findViewById(R.id.btn_Back) as Button
//
//        btn_Back.setOnClickListener (View.OnClickListener {
//            commonNavActivity.change_to_Menu(20)
//        })
        return view
    }


    /*** deleteUser(uid :String) -  (해당) User 탈퇴
     *  Param1 : String (uid)
     ***/
    fun deleteUser(uid :String,view:View) {

        Log.d(ContentValues.TAG, "SERVICE - deleteUser")

        // TODO ("알림창 만들어야함 - "정말 탈퇴 하시겠습니까?" ")

        val prePassword = binding.inputRePw2.text.toString()
        val checkBox = binding.checkBox.onCheckIsTextEditor()

        if(prePassword.isEmpty() || prePassword.equals("")) {
            Toast.makeText(commonNavActivity, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show()
            return
        }

        if(!checkBox) {
            Toast.makeText(commonNavActivity, "안내사항을 체크하셔야 탈퇴가 진행됩니다.", Toast.LENGTH_LONG).show()
            return
        }

//        if(prePassword) {
//            Toast.makeText(commonNavActivity, "안내사항을 체크하셔야 탈퇴가 진행됩니다.", Toast.LENGTH_LONG).show()
//            return
//        }



        /*** FireBase에서 삭제 ***/
//        //val user = FBAuth.auth.currentUser!!
//        val user = FBAuth.getCurrentUser()!!
//        FBRef.userInfoRef.child(uid).removeValue()
//        user.delete()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "User account deleted.")
//                }
//            }
//
//        //로그아웃처리
//        FirebaseAuth.getInstance().signOut()

//        val intent = Intent(commonNavActivity, LoginActivity::class.java)
//        startActivity(intent)

    }
}