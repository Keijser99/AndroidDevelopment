package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_link.view.*

class LinkAdapter(private val links: List<Link>, val clickListener: (Link) -> Unit): RecyclerView.Adapter<LinkAdapter.ViewHolder>() {

    //This values are used to build an url for the link
    val webProtocol: String = "https://"
    val subDomain: String = "www."

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(link : Link, clickListener: (Link) -> Unit) {



//            itemView.tvTitle.text = link.linkTitle
//            itemView.tvLink.text = webProtocol + subDomain + link.linkAddress

//            //For testing the click event
//            itemView.setOnClickListener{
//
//                //Still need to add link for site
//                Snackbar.make(itemView, "Link To Portal: " + link.linkAddress , Snackbar.LENGTH_SHORT).show()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(links[position], clickListener)
    }
}