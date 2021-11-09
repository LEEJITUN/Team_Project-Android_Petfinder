package com.project.petfinder.firebaseuser


import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.project.petfinder.Nav.CommonNavActivity
import com.project.petfinder.utils.FBRef
import java.io.ByteArrayOutputStream


/*** FireBase User Service ***/
class UserService {

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var database: FirebaseDatabase

    lateinit var userModel : UserModel


    // 성공,실패
    var flag = false

    /***
     *  constructor - 생성자
     ***/
    constructor(){
        auth = Firebase.auth
        storage = FirebaseStorage.getInstance()
        database = FirebaseDatabase.getInstance()
        userModel =  UserModel()
        
        Log.d(TAG,"여기1111")
    }

    /***
     *  createFireBaseAuthUser(UserModel,AppCompatActivity) - FireBase에 FireBaseAuth 사용자,UserModel 객체를 생성
     *  @Param1 : UserModel 객체
     *  @Param2 : 사용할 페이지의 해당 AppCompatActivity 객체
     *  @Param3 : 이동할  페이지의 해당 Any(AppCompatActivity가 들어옴) 객체
     *  @return
     ***/
    fun createFireBaseAuthUser(userModel : UserModel,context : AppCompatActivity,movePage :Any ){

        // id,pw 할당
        val inputEmail = userModel.userEmail
        val inputPw = userModel.userPassword

        // null Check
        if (!(NullCheck(inputEmail) || NullCheck(inputPw))) {

            // 원래 회원일 경우 -> 업데이트만 시킴 
            auth.signInWithEmailAndPassword(userModel.userEmail, userModel.userPassword)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        val uid = task.getResult()?.user?.uid
                        FBRef.userInfoRef.child(uid!!).child("userName").setValue(userModel.userName)
                        FBRef.userInfoRef.child(uid!!).child("phone").setValue(userModel.phone)
                        FBRef.userInfoRef.child(uid!!).child("profileImageUrl").setValue(userModel.profileImageUrl)


                        // 메인으로 이동
                        val intent = Intent(context, CommonNavActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("uid",uid)
                        context.startActivity(intent)

                    } else { // 새로운 회원일 경우 추가
                        // firebase auth user create
                        auth.createUserWithEmailAndPassword(userModel.userEmail, userModel.userPassword).addOnCompleteListener(context) { task ->

                            if (task.isSuccessful) {
                                val uid = task.getResult()?.user?.uid
                                if (uid != null) {
                                    userModel.uid = uid
                                    // database에 저장
                                    database.getReference().child("users").child(userModel.uid).setValue(userModel)
                                    flag = true

                                    // 이동
                                    val intent = Intent(context, movePage as Class<*>)
                                    //uid넘김
                                    intent.putExtra("uid", userModel.uid)
                                    context.startActivity(intent)
                                }
                            } else {
                                Toast.makeText(context, "실패", Toast.LENGTH_LONG).show()
                            }
                        }

                    }
                }
        }
    }


    /***
     *  NullCheck() - 해당 String data가 null or "" 인지 알려주는 메소드
     *  @Param1 : String
     *  @return : Boolean ( null(0) : true or null(x) : false)
     ***/
    fun NullCheck(inputData : String):Boolean {
        return inputData.isEmpty() || inputData.equals("")
    }

}

