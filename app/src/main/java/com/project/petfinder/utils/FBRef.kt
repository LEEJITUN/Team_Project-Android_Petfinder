package com.project.petfinder.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * @Class : FBRef Class
 * @Description : Firebase에서는 데이터를 읽거나 쓰려면 DatabaseReference의 인스턴스가 필요하고
 * Firebase의 database 쓰는 곳이 많아 공유 객체로 만듬
 *
 * companion object 이란?
 * static과 유사하게 (단 companion 객체는 스테틱이 아님 비슷하게 쓸뿐)
 * 어떤 클래스의 모든 인스턴스가 공유하는 객체를 만들고 싶을 때 사용하며
 * 클래스당 한 개만 가질 수 있음.
 */
class FBRef {

    companion object {

        private val database = Firebase.database

        val category1 = database.getReference("contents")
        val category2 = database.getReference("contents2")

        val bookmarkRef = database.getReference("bookmark_list")

        val boardRef = database.getReference("board")

        val commentRef = database.getReference("comment")

        /** 데이터베이스 Users의 인스턴스(파베에 있는 json 데이터에서 users 부분) **/
        val userInfoRef = database.getReference("users")

        val codeRef = database.getReference("code")

    }

}