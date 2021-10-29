package com.project.petfinder.fragment.child

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Findertalk_MyRecyclerViewAdapter
import com.project.petfinder.DataModel
import com.project.petfinder.R

class Findertalk_adoptAndForstering_Fragment : Fragment(), Findertalk_MyRecyclerViewAdapter.ClickListener {

    private lateinit var adapter: Findertalk_MyRecyclerViewAdapter
    val listData: ArrayList<DataModel> = ArrayList()

//    companion object {
//        fun newInstance() : Findertalk_adoptAndForstering_Fragment = Findertalk_adoptAndForstering_Fragment()
//    } 테스트 위해 주석처리(준영)


    private fun initRecyclerView(view:View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = Findertalk_MyRecyclerViewAdapter(listData, this)
        recyclerView.adapter = adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
//        return inflater.inflate(R.layout.activity_findertalk_adopt, container, false)
//    } 테스트 위해 주석처리(준영)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment__findertalk_main, container, false)
        buildDisplayData()
        initRecyclerView(view)
        return view
    }

    private fun buildDisplayData(){
        listData.add(DataModel(R.drawable.tory1, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        listData.add(DataModel(R.drawable.tory2, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        listData.add(DataModel(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        listData.add(DataModel(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        listData.add(DataModel(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        listData.add(DataModel(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
    }
    companion object{
        @JvmStatic
        fun newInstance() = Findertalk_adoptAndForstering_Fragment().apply{
            arguments = Bundle().apply{

            }
        }
    }

    override fun onItemClick(dataModel: DataModel) {
        TODO("Not yet implemented")
    }

//    TODO:상세정보를 위한 코드(곧 기능구현2021_10_29)
//    override fun onItemClick(dataModel: DataModel) {
//        val fragment: Fragment = DetailFragment.newInstance(dataModel.contentName!!)
//        val transaction = activity?.supportFragmentManager!!.beginTransaction()
//        transaction.hide(activity?.supportFragmentManager!!.findFragmentByTag("main_fragment")!!)
//        transaction.add(R.id.frame_container, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

}

