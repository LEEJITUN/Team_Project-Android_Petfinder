package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.petfinder.DataModel
import com.project.petfinder.R

//TODO: 일단 프래그먼트 형태로 바꿨으나, 화면에서 보이지 않음.
class Findertalk_MyRecyclerViewAdapter(val listData: ArrayList<DataModel>,
                                       val clickListener: ClickListener) : RecyclerView.
                                        Adapter<Findertalk_MyRecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): Findertalk_MyRecyclerViewAdapter.MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(
            R.layout.cardview_item,
            parent, false, )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        (holder as MyViewHolder).imageView.setImageResource(listData.get(position).imageview)
        holder.contentName.text = listData.get(position).contentName
        holder.contentColor.text = listData.get(position).contentColor
        holder.contentLostdate.text = listData.get(position).contentLostdate
        holder.contentLostplace.text = listData.get(position).contentLostplace
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(listData.get(position))
        }
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var imageView: ImageView
        var contentName: TextView
        var contentColor: TextView
        var contentLostdate: TextView
        var contentLostplace: TextView

        init{
            imageView = view.findViewById(R.id.cardview_imageview) as ImageView
            contentName = view.findViewById<View>(R.id.cardview_content_name) as TextView
            contentColor = view.findViewById<View>(R.id.cardview_content_color) as TextView
            contentLostdate = view.findViewById<View>(R.id.cardview_content_lostdate) as TextView
            contentLostplace = view.findViewById<View>(R.id.cardview_content_lostplace) as TextView
        }
    }

    override fun getItemCount(): Int {
        return listData.size
        TODO("Not yet implemented")
    }

    interface ClickListener{
        fun onItemClick(dataModel: DataModel)
    }

}