package com.project.petfinder.fragment

import android.content.Context
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.project.petfinder.LoginActivity
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.R
import com.project.petfinder.databinding.ActivityMyInfoBinding
import com.project.petfinder.firebaseuser.UserModel
import com.project.petfinder.utils.FBRef

class MyInfoFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity
    lateinit var uid :String
    lateinit var email :String
    lateinit var binding : ActivityMyInfoBinding


    companion object {
        fun newInstance(): MyInfoFragment = MyInfoFragment()
    }


    /**
     * User 정보 객체 생성
     *
     * - UI적 부분은 onCreateView()가 실행되기 전 실행되야함.
     *
     *             Fragment의 생명주기
     * onAttach() -> onCreate() -> onCreateView() ->
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /************* User 정보 객체 생성  **************/
        uid = commonNavActivity.uid

        // UI적 부분은 onCreateView()가 실행되기 전 실행되야함.
        selectUser(uid)

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
         binding = DataBindingUtil.inflate(inflater, R.layout.activity_my_info, container, false)

        /******** 버튼 클릭 이벤트 ********/

        // 반려동물 등록하기 버튼
        binding.btnRegist.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_2")
        })

        // 반려동물 수정하기 이미지 버튼
        binding.btnModify.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_2")
        })

        // 비밀번호 변경 버튼
        binding.btnPwchange.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_5")
        })

        // 회원 탈퇴 버튼
        binding.btnWithdrawal.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_6")
        })

        // 로그아웃 버튼
        binding.btnLogout.setOnClickListener(View.OnClickListener {
            // firebase-auth에서 로그아웃
            Firebase.auth.signOut()

            Toast.makeText(commonNavActivity, "로그아웃", Toast.LENGTH_LONG).show()

            // 로그아웃 이후 로그인 페이지 이동
            val intent = Intent(commonNavActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })
        /*******************************/

        return binding.getRoot()
    }

    /***
     * @Service: selectUserInfo(uid : String) -  (해당) User 조회
     * @Param1 : String (uid)
     * @Description : 사용자의 uid로 Firebase users객체에 있는 해당 uid 사용자의 정보를 찾음
     ***/
    fun selectUser(uid :String) {
        Log.d(TAG, "SERVICE - selectUser")

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // Firebase에 담긴 User를 UserModel 객체로 가져옴.
                val userModel = dataSnapshot.getValue(UserModel::class.java)

                /*********** UI Setting ************/
                binding.myName.setText(userModel?.userName)
                binding.inputPhoneNum.setText(userModel?.phone)
                binding.inputEmail.setText(userModel?.userEmail)

                // 이메일값 담기 (비밀번호 변경때 쓰기 위해서)
                commonNavActivity.email = userModel?.userEmail.toString()

                // User Porfile 값이 "EMPTY" 가 아닐때만 프로필 셋팅
                if(!userModel?.profileImageUrl.equals("EMPTY")){
                    Glide.with(commonNavActivity)
                        .load(userModel?.profileImageUrl)
                        .into(binding.myProfile)
                }
                /*********************************/

            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        // 파이어베이스에 users객체의 해당 uid에 해당 이벤트를 전달
        FBRef.userInfoRef.child(uid).addValueEventListener(postListener)
    }

}


