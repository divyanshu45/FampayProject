package com.example.fampayproject.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fampayproject.R
import com.example.fampayproject.model.Card
import com.example.fampayproject.utils.AppPreferences
import com.google.gson.Gson

class ChildAdapter(private val childCards: ArrayList<Card>, private val type: Int, private val activity: Activity)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class DataViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.tv_title_hc1)
        val image: ImageView = itemView.findViewById(R.id.iv_img_hc1)
        val cardView: CardView = itemView.findViewById(R.id.cv_hc1)
        fun bind(position: Int){
            val item = childCards[position]
            val url = item.url
            title.text = item.title
            if(item.bg_color != null){
                cardView.setCardBackgroundColor(Color.parseColor(item.bg_color))
            }
            Glide.with(activity).load(item.icon.image_url).into(image)
            if(!item.is_disabled) {
                itemView.setOnClickListener {
                    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            }
        }
    }

    private inner class DataViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.tv_titlehc3)
        val desc: TextView = itemView.findViewById(R.id.tv_deschc3)
        val image: ImageView = itemView.findViewById(R.id.iv_imghc3)
        val rlMain: RelativeLayout = itemView.findViewById(R.id.rl_main)
        val llExtra: LinearLayout = itemView.findViewById(R.id.ll_extra)
        val llAlert: LinearLayout = itemView.findViewById(R.id.ll_alert)
        val llDismiss: LinearLayout = itemView.findViewById(R.id.ll_dismiss)
        val button: Button = itemView.findViewById(R.id.bt_actionhc3)

//        val jsonString = AppPreferences.removedCardsJson.toString()
//        val removedCardList: ArrayList<String> = if(jsonString != ""){
//            Gson().fromJson(jsonString, Array<String>::class.java).asList() as ArrayList<String>
//        }else{
//            ArrayList()
//        }


        fun bind(position: Int){
            val item = childCards[position]
            val cta = item.cta[0]
            val url = item.url
            val btnUrl = cta.url
            title.text = item.title
            desc.text = item.formatted_description.text
            rlMain.setBackgroundColor(Color.parseColor(item.bg_color))
            Glide.with(activity).load(item.bg_image.image_url).into(image)
            button.text = cta.text
            button.setTextColor(Color.parseColor(cta.text_color))
            button.setBackgroundColor(Color.parseColor(cta.bg_color))
            if(!item.is_disabled){
                button.setOnClickListener {
                    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(btnUrl)))
                }
            }
            itemView.setOnClickListener {
                activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
            itemView.setOnLongClickListener {
                if(llExtra.visibility == View.VISIBLE){
                    llExtra.visibility = View.GONE
                }else{
                    llExtra.visibility = View.VISIBLE
                }
                true
            }
            llAlert.setOnClickListener {
                removeItem(position)
                llExtra.visibility = View.GONE
                Toast.makeText(activity, "Alert Clicked", Toast.LENGTH_SHORT).show()
            }
            llDismiss.setOnClickListener {
//                removedCardList.add(item.name)
//
//                val jsonText = Gson().toJson(removedCardList as ArrayList<String>)
//                AppPreferences.removedCardsJson = jsonText
                removeItem(position)

                llExtra.visibility = View.GONE
                Toast.makeText(activity, "Dismiss Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private inner class DataViewHolder5(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView: CardView = itemView.findViewById(R.id.cv_hc5)
        val icon: ImageView = itemView.findViewById(R.id.iv_imagehc5)
        fun bind(position: Int){
            val item = childCards[position]
            val url = item.url
            cardView.setCardBackgroundColor(Color.parseColor(item.bg_color))
            Glide.with(activity).load(item.bg_image.image_url).into(icon)
            if(!item.is_disabled){
                itemView.setOnClickListener {
                    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            }
        }
    }

    private inner class DataViewHolder6(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.tv_namehc6)
        val icon: ImageView = itemView.findViewById(R.id.iv_imghc6)
        fun bind(position: Int){
            val item = childCards[position]
            val url = item.url
            title.text = item.title
            Glide.with(activity).load(item.icon.image_url).into(icon)
            if(!item.is_disabled){
                itemView.setOnClickListener {
                    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            }
        }
    }

    private inner class DataViewHolder9(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.iv_imagehc9)
        fun bind(position: Int) {
            val item = childCards[position]
            val url = item.url
            Glide.with(activity).load(item.bg_image.image_url).into(image)
            if(!item.is_disabled){
                itemView.setOnClickListener {
                    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (type) {
            1 -> {
                return DataViewHolder1(
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_hc1, parent, false)
                )
            }
            3 -> {
                return DataViewHolder3(
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_hc3, parent, false)
                )
            }
            5 -> {
                return DataViewHolder5(
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_hc5, parent, false)
                )
            }
            6 -> {
                return DataViewHolder6(
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_hc6, parent, false)
                )
            }
            else -> {
                return DataViewHolder9(
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_hc9, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(type){
            1->{
                (holder as DataViewHolder1).bind(position)
            }
            3->{
                (holder as DataViewHolder3).bind(position)
            }
            5->{
                (holder as DataViewHolder5).bind(position)
            }
            6->{
                (holder as DataViewHolder6).bind(position)
            }
            9->{
                (holder as DataViewHolder9).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return childCards.size
    }

    private fun removeItem(position: Int) {
        childCards.removeAt(position)
        notifyDataSetChanged()
    }

}