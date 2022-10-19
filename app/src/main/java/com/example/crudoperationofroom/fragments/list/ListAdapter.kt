package com.example.crudoperationofroom.fragments.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crudoperationofroom.R
import com.example.crudoperationofroom.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.txtid.text = currentItem.id.toString()
        holder.itemView.txtfirstname.text = currentItem.firstName
        holder.itemView.txtlastname.text = currentItem.lastName
        holder.itemView.txtage.text = currentItem.age.toString()

        holder.itemView.rowlistlayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment2(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()

    }

}