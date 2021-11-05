package com.project.petfinder.firebaseuser

class UserModel {
    lateinit var userEmail : String
    lateinit var userPassword : String
    lateinit var userName: String
    lateinit var profileImageUrl: String
    lateinit var uid: String
    lateinit var phone : String

    constructor()
    constructor(
        userEmail: String,
        userPassword: String,
        userName: String,
        phone: String,
        profileImageUrl :String
    ) {
        this.userEmail = userEmail
        this.userPassword = userPassword
        this.userName = userName
        this.phone = phone
        this.profileImageUrl = profileImageUrl
    }

}