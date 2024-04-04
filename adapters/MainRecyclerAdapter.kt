package com.prep.lyft.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prep.lyft.R
import com.prep.lyft.databinding.ActivityViewPdfBinding
import com.prep.lyft.databinding.EachItemBinding
import com.prep.lyft.databinding.SingleTutorialItemBinding
import com.prep.lyft.models.MainDataItem
import com.prep.lyft.models.RecyclerEachItem
import com.prep.lyft.models.TutorialItem
import com.prep.lyft.utils.DataItemType

class MainRecyclerAdapter(private val dataItemList: List<MainDataItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class RecyclerEachItemViewHolder(private val binding: EachItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.listEachItem.setHasFixedSize(true)
            binding.listEachItem.layoutManager=LinearLayoutManager(binding.root.context,RecyclerView.HORIZONTAL,false)
        }
        fun bindCourseViewHolder(recyclerEachItemList: List<RecyclerEachItem>){
            val adapter=ChildRecyclerAdapter(DataItemType.COURSE,recyclerEachItemList)
            binding.listEachItem.adapter=adapter
        }
        fun bindCheatViewHolder(recyclerEachItemList: List<RecyclerEachItem>){
            val adapter=ChildRecyclerAdapter(DataItemType.CHEATSHEET,recyclerEachItemList)
            binding.listEachItem.adapter=adapter
        }
    }
    inner class TutorialItemViewHolder(private val binding: SingleTutorialItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindTutorialViewHolder(tutorialItem: TutorialItem){
            binding.txtTutorialTitle.text=tutorialItem.title
            binding.txtTutorialDate.text=tutorialItem.date
            binding.txtTutorialDuration.text=tutorialItem.duration
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.single_tutorial_item->{
                val binding=SingleTutorialItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                TutorialItemViewHolder(binding)
            }
            else->{
                val binding=EachItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                RecyclerEachItemViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(dataItemList[position].viewType){
            DataItemType.TUTORIAL->{
                R.layout.single_tutorial_item
            }
            else->{
                R.layout.each_item
            }
        }
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TutorialItemViewHolder->{
                dataItemList[position].tutorialItem?.let { holder.bindTutorialViewHolder(it) }
            }
            else->{
                when(dataItemList[position].viewType){
                    DataItemType.COURSE->{
                        dataItemList[position].recyclerEachItemList?.let { (holder as RecyclerEachItemViewHolder).bindCourseViewHolder(it) }
                    }
                    else->{
                        dataItemList[position].recyclerEachItemList?.let { (holder as RecyclerEachItemViewHolder).bindCheatViewHolder(it) }
                    }
                }
            }
        }
    }

}