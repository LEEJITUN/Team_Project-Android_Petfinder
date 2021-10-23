package com.project.petfinder

import androidx.recyclerview.widget.RecyclerView
import com.project.petfinder.CardViewItemDTO
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.project.petfinder.R
import com.project.petfinder.Findertalk_MyRecyclerViewAdapter.RowCell
import android.widget.TextView
import java.util.ArrayList

class Findertalk_MyRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val cardViewItemDTOs = ArrayList<CardViewItemDTO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //XML세팅
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_item, parent, false)
        return RowCell(view) //스태틱이므로 create inner class 'RowCell'
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RowCell).imageView.setImageResource(cardViewItemDTOs[position].imageview)
        holder.contentName.text = cardViewItemDTOs[position].contentName
        holder.contentColor.text = cardViewItemDTOs[position].contentColor
        holder.contentLostdate.text = cardViewItemDTOs[position].contentLostdate
        holder.contentLostplace.text = cardViewItemDTOs[position].contentLostplace
        //아이템 세팅
    }

    override fun getItemCount(): Int {
        //이미지 카운터
        return cardViewItemDTOs.size
    }

    private inner class RowCell(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var contentName: TextView
        var contentColor: TextView
        var contentLostdate: TextView
        var contentLostplace: TextView

        init {
            imageView = view.findViewById<View>(R.id.cardview_imageview) as ImageView
            contentName = view.findViewById<View>(R.id.cardview_content_name) as TextView
            contentColor = view.findViewById<View>(R.id.cardview_content_color) as TextView
            contentLostdate = view.findViewById<View>(R.id.cardview_content_lostdate) as TextView
            contentLostplace = view.findViewById<View>(R.id.cardview_content_lostplace) as TextView
        }
    }

    init {
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory1, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory2, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory3, "[개] 시바이누", "적갈색|2018(년생)","잃어버린 날짜: 2021.09.28.","잃어버린 장소: 창평면 의병로 101"))

    }
}