package com.prep.lyft.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prep.lyft.databinding.SingleCheatItemBinding
import com.prep.lyft.databinding.SingleCourseItemBinding
import com.prep.lyft.models.RecyclerEachItem
import com.prep.lyft.utils.DataItemType

class ChildRecyclerAdapter(private val viewType: Int, private val recyclerEachItemList: List<RecyclerEachItem>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CourseItemViewHolder(private val binding: SingleCourseItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCourseView(recyclerEachItem: RecyclerEachItem){
            binding.txtCourseTitle.text=recyclerEachItem.title
            binding.txtCourseDate.text=recyclerEachItem.date
        }
    }
    inner class CheatItemViewHolder(private val binding: SingleCheatItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCheatView(recyclerEachItem: RecyclerEachItem){
            binding.txtCheatTitle.text=recyclerEachItem.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            DataItemType.COURSE->{
                val binding=SingleCourseItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                CourseItemViewHolder(binding)
            }
            else->{
                val binding=SingleCheatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                CheatItemViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun getItemCount(): Int {
        return recyclerEachItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(holder){
           is CourseItemViewHolder->{
               holder.bindCourseView(recyclerEachItemList[position])
           }
           is CheatItemViewHolder->{
               holder.bindCheatView(recyclerEachItemList[position])
           }
       }
    }
}