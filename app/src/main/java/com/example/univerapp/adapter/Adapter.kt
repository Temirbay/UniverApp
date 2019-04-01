package com.example.univerapp.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.univerapp.R
import com.example.univerapp.entity.Answer
import com.example.univerapp.entity.Course
import kotlinx.android.synthetic.main.item_answer.view.*
import kotlinx.android.synthetic.main.item_course.view.*


class Adapter(private val context : Context,
              private val items : ArrayList<Any>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onCourseClickListener : OnCourseClickListener ?= null
    private var onAnswerClickListener : OnAnswerClickListener ?= null

    object HolderTypes {
        const val Course = 0
        const val Answer = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            HolderTypes.Course -> {
                CourseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false))
            }
            else -> {
                AnswerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is Course -> HolderTypes.Course
            else -> HolderTypes.Answer
        }
    }

    fun updateList (list : List<Any>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnAnswerClickListener (listener : OnAnswerClickListener) {
        this.onAnswerClickListener = listener
    }

    fun setOnCourseClickListener (listener : OnCourseClickListener) {
        this.onCourseClickListener = listener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CourseViewHolder -> {
                val course = items[position] as Course
                holder.itemView.tvTitle.text = course.title
                holder.itemView.bnInfo.setOnClickListener {
                    onCourseClickListener?.onInfoShowClick(course)
                }
                holder.itemView.bnTest.setOnClickListener {
                    onCourseClickListener?.onTestShowClick(course)
                }
            }
            is AnswerViewHolder -> {
                val answer = items[position] as Answer
                holder.itemView.answer.text = answer.text
                holder.itemView.bnAnswer.isChecked = false
                holder.itemView.bnAnswer.isEnabled = true
                holder.itemView.answer.setTextColor(Color.BLACK)

                holder.itemView.bnAnswer.setOnClickListener {
                    if (answer.isCorrect == 0) {
                        holder.itemView.answer.setTextColor(Color.RED)
                        holder.itemView.bnAnswer.isEnabled = false
                    }
                    onAnswerClickListener?.onAnswerClick(answer)
                }

                holder.itemView.answer.setOnClickListener {
                    if (answer.isCorrect == 0) {
                        holder.itemView.answer.setTextColor(Color.RED)
                        holder.itemView.bnAnswer.isEnabled = false
                    }
                    else {
                        holder.itemView.answer.setTextColor(context.resources.getColor(R.color.colorPrimary))
                        holder.itemView.bnAnswer.isEnabled = false
                    }

                    holder.itemView.bnAnswer.toggle()
                    onAnswerClickListener?.onAnswerClick(answer)
                }
            }
        }
    }

    inner class CourseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class AnswerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnAnswerClickListener {
        fun onAnswerClick (answer : Answer)
    }

    interface OnCourseClickListener {
        fun onTestShowClick (course: Course)
        fun onInfoShowClick (course: Course)
    }


}