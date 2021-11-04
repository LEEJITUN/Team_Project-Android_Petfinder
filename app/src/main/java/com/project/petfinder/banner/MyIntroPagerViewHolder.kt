package com.project.petfinder.banner

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.banner.view.*

class MyIntroPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemBg = itemView.pager_item_bg

    fun bindWithView(pageItem: PageItem){
        itemBg.setBackgroundResource(pageItem.imageSrc)
    }

}