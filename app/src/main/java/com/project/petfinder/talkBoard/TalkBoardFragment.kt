package com.project.petfinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.project.petfinder.BoardListVAdapter
import com.project.petfinder.R
import com.project.petfinder.databinding.FragmentTalkBoardBinding
import com.project.petfinder.talkBoard.DataModel


//val 변경불가 var 변경가능
class TalkBoardFragment : Fragment() {

    private lateinit var binding : FragmentTalkBoardBinding

    private val boardDataList = mutableListOf<DataModel>()
    private val boardKeyList = mutableListOf<String>()

    //TODO: 보드의 시작점이 될 프래그먼트
    private val TAG = TalkBoardFragment::class.java.simpleName

    private lateinit var boardRVAdapter : BoardListVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk_board, container, false)

        boardRVAdapter = BoardListVAdapter(boardDataList)
        return inflater.inflate(R.layout.fragment_talk_board, container, false)
    }

    //이거는 static처럼 쓸 때 사용하는 것.
    companion object {
        fun newInstance() : TalkBoardFragment = TalkBoardFragment()
    }

}