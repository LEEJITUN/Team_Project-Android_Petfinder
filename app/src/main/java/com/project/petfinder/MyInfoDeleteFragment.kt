package com.project.petfinder

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

class MyInfoDeleteFragment : Fragment() {
    lateinit var commonNavActivity: CommonNavActivity

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

        var view: View =
            inflater.inflate(R.layout.activity_my_info_delete, container, false) //반환형 : View }

        // 확인 버튼 클릭 시 -> 내정보 화면으로 이동
        var btnSubmit = view?.findViewById(R.id.btnSubmit) as Button

        btnSubmit.setOnClickListener(View.OnClickListener {

            deleteUser(commonNavActivity.uid)
            commonNavActivity.change_to_Menu("MY_8")

        })

        // 취소 버튼 클릭 시 -> 내정보 화면으로 이동
        var btnCancel = view?.findViewById(R.id.btnCancel) as Button

        btnCancel.setOnClickListener(View.OnClickListener {

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
    fun deleteUser(uid :String) {

        Log.d(ContentValues.TAG, "SERVICE - deleteUser")

        // TODO ("알림창 만들어야함 - "정말 탈퇴 하시겠습니까?" ")


        /*** FireBase에서 삭제 ***/
        //val user = FBAuth.auth.currentUser!!
        val user = FBAuth.getCurrentUser()!!
        FBRef.userInfoRef.child(uid).removeValue()
        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User account deleted.")
                }
            }

        //로그아웃처리
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(commonNavActivity, LoginActivity::class.java)
        startActivity(intent)

    }
}