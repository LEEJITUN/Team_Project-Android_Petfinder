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
        holder.title.text = cardViewItemDTOs[position].title
        holder.subtitle.text = cardViewItemDTOs[position].subtitle
        //아이템 세팅
    }

    override fun getItemCount(): Int {
        //이미지 카운터
        return cardViewItemDTOs.size
    }

    private inner class RowCell(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var title: TextView
        var subtitle: TextView

        init {
            imageView = view.findViewById<View>(R.id.cardview_imageview) as ImageView
            title = view.findViewById<View>(R.id.cardview_title) as TextView
            subtitle = view.findViewById<View>(R.id.cardview_subtitle) as TextView
        }
    }

    init {
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory1, "첫번째", "사진1"))
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory2, "두번째", "사진2"))
        cardViewItemDTOs.add(CardViewItemDTO(R.drawable.tory3, "세번째", "사진3"))
    }
}