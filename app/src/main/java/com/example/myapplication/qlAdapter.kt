package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class qlAdapter : RecyclerView.Adapter<qlAdapter.qlViewHolder>() {
    private var stdList: ArrayList<quanlymodel> = ArrayList()
    private var deleteListener: OnItemDeleteListener? = null

    interface OnItemDeleteListener {
        fun onDeleteClick(position: Int)
    }

    fun addItems(items: ArrayList<quanlymodel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): qlViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_ql, parent, false)
        return qlViewHolder(view)

    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    override fun onBindViewHolder(holder: qlViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
//        holder.btnDelete.setOnClickListener {
//            deleteListener?.onDeleteClick(position)
//        }
    }

    class qlViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        private val id = view.findViewById<TextView>(R.id.tvid)
        private val name = view.findViewById<TextView>(R.id.tvname)
        private val password = view.findViewById<TextView>(R.id.tvpassword)
        private val email = view.findViewById<TextView>(R.id.tvemail)
        private val phone = view.findViewById<TextView>(R.id.tvphone)
//        private val btnDelete = view.findViewById<TextView>(R.id.bt)

        fun bindView(std: quanlymodel) {
//            id.text = std.id.toString()
            name.text = std.name
            password.text = std.password
            email.text = std.email
            phone.text = std.phone
        }
    }
}
