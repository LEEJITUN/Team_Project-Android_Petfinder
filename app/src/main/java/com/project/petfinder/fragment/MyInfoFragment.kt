package com.project.petfinder.fragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.project.petfinder.LoginActivity
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.R
import com.project.petfinder.databinding.ActivityMyInfoBinding
import com.project.petfinder.firebaseuser.UserModel
import com.project.petfinder.utils.FBRef
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import androidx.core.app.ActivityCompat.startActivityForResult
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_my_info.*
import java.io.InputStream
import java.lang.Exception


class MyInfoFragment : Fragment() {

    lateinit var commonNavActivity: CommonNavActivity
    lateinit var uid :String
    lateinit var email :String
    lateinit var binding : ActivityMyInfoBinding
    var isImageUpload = false

//    lateinit var resetImageButton: FloatingActionButton

    companion object {
        fun newInstance(): MyInfoFragment = MyInfoFragment()
        lateinit var profileImageView: CircleImageView //가장 중요한 이미지 뷰 컴퍼넌트!!
        lateinit var resetImageButton: FloatingActionButton //가장 중요한 이미지 뷰 컴퍼넌트!!
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


        profileImageView = binding.myProfile //CircleImageView
        //resetImageButton = binding.init_profileimg_reset_btn  //이미지 리셋 버튼

//        resetImageButton.setOnClickListener{
//            //라이브러리의 이미지 자르기용 액티비티 호출
//            CropImage.activity().setAspectRatio(1, 1).start(commonNavActivity as Activity)
//            //이때 ProfileInitContext는 위에 만든 프래그먼트가 종속될 일반 XML파일의 Context를 담은 전역변수
//            //이미지 크롭 완료 후 ProfileInitActivity.onAcitivtyResult 실행
//        }
        CropImage.activity().setAspectRatio(1, 1).start(commonNavActivity as Activity)
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
            it.findNavController().navigate(R.id.action_myInfoFragment_to_myInfoChangePwFragment)
          //  commonNavActivity.change_to_Menu("MY_5")
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

        // 프로필 사진 클릭
        binding.myProfile.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {

                val temp = CoroutineScope(Dispatchers.Default).async {

//                    var intent = Intent()
//                    intent.setType("image/*")
//                    intent.setAction(Intent.ACTION_GET_CONTENT)
//                    startActivityForResult(intent, 1)

                    var intent =  Intent()
                    intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    intent.setType("image/*")
                    intent.setAction(Intent.ACTION_GET_CONTENT)

                }.await()

                    Log.d(TAG,"???"+isImageUpload)
                    imageUpload(uid)


            }

        }
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


    /***
     *  imageUpload(UserModel,AppCompatActivity) - FireBase의 storage에 이미지 업로드 및 users에 profileUrl 저장
     *  @Param1 : String(uid)
     *  @return
     ***/
    fun imageUpload(uid:String){


        Log.d(TAG,"imageUpload")
        Log.d(TAG,"44444444444444")
        // Get the data from an ImageView as bytes
        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child(uid + ".png")

        // Create a reference to 'images/mountains.jpg'
        val mountainImagesRef = storageRef.child("images/"+uid+".png")

        mountainsRef.name == mountainImagesRef.name // true
        mountainsRef.path == mountainImagesRef.path // false

        Log.d(TAG,"myProfile: " + binding.myProfile)

        val imageView = binding.myProfile
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()

        Log.d(TAG,"drawable: " + imageView.drawable)

        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        Log.d(TAG,"666666666666")
        var uploadTask = mountainsRef.putBytes(data)

        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
            // firebase-database에서 UserModel의 userPassword 수정
            FBRef.userInfoRef.child(uid).child("profileImageUrl").setValue(mountainsRef.name)
            Toast.makeText(commonNavActivity, "프로필 수정", Toast.LENGTH_LONG).show()

        Log.d(TAG,"77777777777777777")
            // 내정보 페이지로 이동
            commonNavActivity.change_to_Menu("MY_8")
        Log.d(TAG,"888888888888888")

    }

}


