package com.project.petfinder.firebaseuser


import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.project.petfinder.Join_User_PetInfo_Activity
import kotlinx.coroutines.*
import java.util.*

/*** FireBase User Service ***/
class UserService {

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var database: FirebaseDatabase

    // 성공,실패
    var flag = false


    /***
     *  constructor - 생성자
     ***/
    constructor(){
        auth = Firebase.auth
        storage = FirebaseStorage.getInstance()
        database = FirebaseDatabase.getInstance()
        
        Log.d(TAG,"여기1111")
    }

    /*** createFireBaseAuthUser(UserModel,AppCompatActivity) - FireBase에 FireBaseAuth 사용자,UserModel 객체를 생성
     *  Param1 : UserModel 객체
     *  Param2 : 사용할 페이지의 해당 AppCompatActivity 객체
     *  retur : Boolean ( 성공 : true or 실패 : false)
     ***/
    fun createFireBaseAuthUser(userModel : UserModel,context : AppCompatActivity,movePage :Any ) : Boolean{

            // id,pw 할당
            val inputEmail = userModel.userEmail
            val inputPw = userModel.userPassword

            // null Check
            if (!(NullCheck(inputEmail) || NullCheck(inputPw))) {

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
                            context.startActivity(intent)

                        }

                    } else {
                        Toast.makeText(context, "실패", Toast.LENGTH_LONG).show()
                    }
                }
            }

        return flag
    }

    /***
     *  modifyUserInfo()
     *  만들 예정 -뚜미니 만들고 싶다면 여기다가 ㅎㅎ
     ***/
    fun modifyUserInfo(){

    }

    /***
     *  modifyUserProfile()
     *  만들 예정 -뚜미니 만들고 싶다면 여기다가 ㅎㅎ
     ***/
    fun modifyUserProfile(){
        
    }


    /***
     *  NullCheck() - 해당 String data가 null or "" 인지 알려주는 메소드
     *  Param1 : String
     *  return : Boolean ( null(0) : true or null(x) : false)
     ***/
    fun NullCheck(inputData : String):Boolean {
        return inputData.isEmpty() || inputData.equals("")
    }




}