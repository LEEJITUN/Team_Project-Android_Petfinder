package com.project.petfinder.fragment.child

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.project.petfinder.R


//private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"

class DetailFragment : Fragment() {
//    private var param1: Image? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null
    private var param5: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
            param5 = it.getString(ARG_PARAM5)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

//        val imageview =  view.findViewById(R.id.cardview_imageview) // important to use this variable
        val contentName = view.findViewById<TextView>(R.id.cardview_content_name) // important to use this variable
        val contentColor = view.findViewById<TextView>(R.id.cardview_content_color) // important to use this variable
        val contentLostdate = view.findViewById<TextView>(R.id.cardview_content_lostdate) // important to use this variable
        val contentLostplace = view.findViewById<TextView>(R.id.cardview_content_lostplace) // important to use this variable


        contentName.text = param2
        contentColor.text = param3
        contentLostdate.text = param4
        contentLostplace.text = param5
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1:String ) =
            DetailFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                }
            }
    }
}
