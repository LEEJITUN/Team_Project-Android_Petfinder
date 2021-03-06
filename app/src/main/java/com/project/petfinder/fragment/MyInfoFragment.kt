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
        lateinit var profileImageView: CircleImageView //?????? ????????? ????????? ??? ????????????!!
        lateinit var resetImageButton: FloatingActionButton //?????? ????????? ????????? ??? ????????????!!
    }

    /**
     * User ?????? ?????? ??????
     *
     * - UI??? ????????? onCreateView()??? ???????????? ??? ???????????????.
     *
     *             Fragment??? ????????????
     * onAttach() -> onCreate() -> onCreateView() ->
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /************* User ?????? ?????? ??????  **************/
        uid = commonNavActivity.uid

        // UI??? ????????? onCreateView()??? ???????????? ??? ???????????????.
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

        // binding ??????
         binding = DataBindingUtil.inflate(inflater, R.layout.activity_my_info, container, false)


        profileImageView = binding.myProfile //CircleImageView
        //resetImageButton = binding.init_profileimg_reset_btn  //????????? ?????? ??????

//        resetImageButton.setOnClickListener{
//            //?????????????????? ????????? ???????????? ???????????? ??????
//            CropImage.activity().setAspectRatio(1, 1).start(commonNavActivity as Activity)
//            //?????? ProfileInitContext??? ?????? ?????? ?????????????????? ????????? ?????? XML????????? Context??? ?????? ????????????
//            //????????? ?????? ?????? ??? ProfileInitActivity.onAcitivtyResult ??????
//        }
        CropImage.activity().setAspectRatio(1, 1).start(commonNavActivity as Activity)
        /******** ?????? ?????? ????????? ********/

        // ???????????? ???????????? ??????
        binding.btnRegist.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_2")
        })

        // ???????????? ???????????? ????????? ??????
        binding.btnModify.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_2")
        })

        // ???????????? ?????? ??????
        binding.btnPwchange.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.action_myInfoFragment_to_myInfoChangePwFragment)
          //  commonNavActivity.change_to_Menu("MY_5")
        })

        // ?????? ?????? ??????
        binding.btnWithdrawal.setOnClickListener(View.OnClickListener {
            commonNavActivity.change_to_Menu("MY_6")
        })

        // ???????????? ??????
        binding.btnLogout.setOnClickListener(View.OnClickListener {
            // firebase-auth?????? ????????????
            Firebase.auth.signOut()

            Toast.makeText(commonNavActivity, "????????????", Toast.LENGTH_LONG).show()

            // ???????????? ?????? ????????? ????????? ??????
            val intent = Intent(commonNavActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })

        // ????????? ?????? ??????
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
     * @Service: selectUserInfo(uid : String) -  (??????) User ??????
     * @Param1 : String (uid)
     * @Description : ???????????? uid??? Firebase users????????? ?????? ?????? uid ???????????? ????????? ??????
     ***/
    fun selectUser(uid :String) {
        Log.d(TAG, "SERVICE - selectUser")

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // Firebase??? ?????? User??? UserModel ????????? ?????????.
                val userModel = dataSnapshot.getValue(UserModel::class.java)

                /*********** UI Setting ************/
                binding.myName.setText(userModel?.userName)
                binding.inputPhoneNum.setText(userModel?.phone)
                binding.inputEmail.setText(userModel?.userEmail)

                // ???????????? ?????? (???????????? ????????? ?????? ?????????)
                commonNavActivity.email = userModel?.userEmail.toString()

                // User Porfile ?????? "EMPTY" ??? ???????????? ????????? ??????
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

        // ????????????????????? users????????? ?????? uid??? ?????? ???????????? ??????
        FBRef.userInfoRef.child(uid).addValueEventListener(postListener)
    }


    /***
     *  imageUpload(UserModel,AppCompatActivity) - FireBase??? storage??? ????????? ????????? ??? users??? profileUrl ??????
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
            // firebase-database?????? UserModel??? userPassword ??????
            FBRef.userInfoRef.child(uid).child("profileImageUrl").setValue(mountainsRef.name)
            Toast.makeText(commonNavActivity, "????????? ??????", Toast.LENGTH_LONG).show()

        Log.d(TAG,"77777777777777777")
            // ????????? ???????????? ??????
            commonNavActivity.change_to_Menu("MY_8")
        Log.d(TAG,"888888888888888")

    }

}


