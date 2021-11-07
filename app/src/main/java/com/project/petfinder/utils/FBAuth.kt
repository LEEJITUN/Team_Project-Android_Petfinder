package com.project.petfinder.utils

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Class : FBAuth
 * @Description : FirebaseAuth를 쓰는 곳이 많아 공유 객체로 만듬
 *
 * companion object 이란?
 * static과 유사하게 (단 companion 객체는 스테틱이 아님 비슷하게 쓸뿐)
 * 어떤 클래스의 모든 인스턴스가 공유하는 객체를 만들고 싶을 때 사용하며
 * 클래스당 한 개만 가질 수 있음.
 */
class FBAuth {

    companion object{

        private lateinit var auth : FirebaseAuth

        /**
         * getUid - FirebaseAuth의 uid 내보냄
         * @return : String (uid)
         */
        fun getUid() : String{

            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }

        /**
         * getCurrentUser - FirebaseAuth의 currentuser를 내보냄
         * @return : null or FirebaseUser
         * @Description : currentUser의 속성을 사용하여 현재 로그인한 사용자를 가져올 수도 있음
         * 사용자가 로그인 상태가 아니라면 currentUser 값이 null
         */
        fun getCurrentUser() : FirebaseUser? {

            auth = FirebaseAuth.getInstance()

            return auth.currentUser
        }

        /**
         * getTime - 현재 시간 구하기
         * @return  : String(시간)
         */
        fun getTime() : String{
            val currentDateTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)

            return dateFormat
        }
    }
}