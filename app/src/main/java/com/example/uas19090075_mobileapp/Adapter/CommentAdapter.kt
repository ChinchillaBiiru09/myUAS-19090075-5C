package com.example.uas19090075_mobileapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas19090075_mobileapp.Model.CommentResponse
import com.example.uas19090075_mobileapp.R
import kotlinx.android.synthetic.main.item_post.view.*

class CommentAdapter(private val listComment: ArrayList<CommentResponse>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(commentResponse: CommentResponse){
            with(itemView){
                val text = "postId : ${commentResponse.postId}\n" +
                        "Id : ${commentResponse.id}\n" +
                        "Name : ${commentResponse.name}\n" +
                        "Email : ${commentResponse.email}\n" +
                        "Body : ${commentResponse.body}"
                tv_Text.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        //inflate layout item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int = listComment.size

}