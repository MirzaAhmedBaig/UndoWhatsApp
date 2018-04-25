package com.undowhatsapp.mirza.undowhatsapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.undowhatsapp.mirza.undowhatsapp.R
import com.undowhatsapp.mirza.undowhatsapp.models.MessageModel

class MessageListAdapter(private val messageModelList: List<MessageModel>) : RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.messageTitle.text = messageModelList[position].title
        holder.message.text = messageModelList[position].msg
        holder.message_time.text = messageModelList[position].time.toString()
    }


    override fun getItemCount(): Int = messageModelList.size


    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val messageTitle: TextView = itemView.findViewById<TextView>(R.id.msg_title)
        val message: TextView = itemView.findViewById<TextView>(R.id.message)
        val message_time: TextView = itemView.findViewById<TextView>(R.id.msg_time)

    }
}

