package com.example.fampayproject.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fampayproject.R
import com.example.fampayproject.model.Card
import com.example.fampayproject.model.CardGroup
import com.example.fampayproject.utils.AppPreferences
import com.google.gson.Gson

class CardsAdapter(private val cards: ArrayList<CardGroup>, private val activity: Activity)
    : RecyclerView.Adapter<CardsAdapter.DataViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_childRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.parent_recycler, parent, false)
        return DataViewHolder(v)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val parent = cards[position]
        val designType = parent.design_type

        val childLayoutManager = when(designType){
            "HC1" ->{
                if(!parent.is_scrollable){
                    GridLayoutManager(holder.recyclerView.context, 2, LinearLayoutManager.VERTICAL, false)
                }else{
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
            "HC3" ->{
                if(!parent.is_scrollable){
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.VERTICAL, false)
                }else{
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
            "HC5" ->{
                if(!parent.is_scrollable){
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.VERTICAL, false)
                }else{
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
            "HC6" ->{
                if(!parent.is_scrollable){
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.VERTICAL, false)
                }else{
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }else -> {
                if(!parent.is_scrollable){
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.VERTICAL, false)
                }else{
                    LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            when(designType){
                "HC1"-> adapter = ChildAdapter(parent.cards as ArrayList<Card>, 1, activity)
                "HC3"-> {
//                    val jsonString = AppPreferences.removedCardsJson.toString()
//                    adapter = if(jsonString == ""){
//                        ChildAdapter(parent.cards as ArrayList, 3, activity)
//                    }else{
//                        val newArrayList = ArrayList<Card>()
//                        val removedCardList: MutableList<String> =
//                            Gson().fromJson(jsonString, Array<String>::class.java).asList() as MutableList<String>
//                        for(item in parent.cards){
//                            if(removedCardList.contains(item.name)){
//                                newArrayList.add(item)
//                            }
//                        }
//                        ChildAdapter(newArrayList, 3, activity)
//                    }
                    adapter = ChildAdapter(parent.cards as ArrayList<Card>, 3, activity)
                }
                "HC5"-> adapter = ChildAdapter(parent.cards as ArrayList<Card>, 5, activity)
                "HC6"-> adapter = ChildAdapter(parent.cards as ArrayList<Card>, 6, activity)
                "HC9"-> adapter = ChildAdapter(parent.cards as ArrayList<Card>, 9, activity)
            }
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun addCards(cards: ArrayList<CardGroup>){
        this.cards.apply {
            clear()
            addAll(cards)
        }
    }
}