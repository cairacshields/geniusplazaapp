package com.example.geniusplazaapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.geniusplazaapp.R
import com.example.geniusplazaapp.objects.Users
import com.squareup.picasso.Picasso

class UsersAdapter(userData: List<Users>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    val mUserData = userData


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUserData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       if(!mUserData.get(position).avatar.isNullOrEmpty()){
           Picasso.get()
               .load(mUserData.get(position).avatar)
               .placeholder(R.drawable.ic_face_black)
               .resize(50, 50)
               .centerCrop()
               .into(holder.imageView)
       }else{
           Picasso.get()
               .load(R.drawable.ic_face_black)
               .placeholder(R.drawable.ic_face_black)
               .resize(50, 50)
               .centerCrop()
               .into(holder.imageView)
       }
        holder.textView!!.text = "${mUserData.get(position).firstName} ${mUserData.get(position).lastName}"
    }


    class ViewHolder: RecyclerView.ViewHolder {

        var imageView: ImageView? = null
        var textView: TextView? = null

        constructor(itemView: View) : super(itemView) {
            imageView = itemView.findViewById(R.id.profile_image)
            textView = itemView.findViewById(R.id.name)
        }


    }
}