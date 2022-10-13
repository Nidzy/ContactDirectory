package com.example.directory.people.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.PeopleDetailActivity
import com.example.directory.R
import com.example.directory.people.model.PeopleDataResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_people.view.*

class PeopleAdapter(var peopleList: MutableList<PeopleDataResponse> = mutableListOf()) :
    RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeopleAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_people, parent, false)
        return PeopleAdapterViewHolder(view)
    }


    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(holder: PeopleAdapterViewHolder, position: Int) {

        if (peopleList.size > 0) {
            val people = peopleList[position]
            holder.itemView.tvName.text = people.firstName
            Picasso.with(holder.itemView.context).load(people.avatar).into(holder.itemView.ivPeople)
            holder.itemView.cvPeople.setOnClickListener {
                val peopleDetailActivity = Intent(it.context, PeopleDetailActivity::class.java)
                peopleDetailActivity.putExtra("firstName", people.firstName)
                peopleDetailActivity.putExtra("lastName", people.lastName)
                peopleDetailActivity.putExtra("email", people.email)
                peopleDetailActivity.putExtra("jobTitle", people.jobtitle)
                peopleDetailActivity.putExtra("favouriteColor", people.favouriteColor)
                peopleDetailActivity.putExtra("avtar", people.avatar)
                peopleDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                it.context.startActivity(peopleDetailActivity)
            }

        }
    }
    fun addData(listOfPeople: List<PeopleDataResponse>) {
        peopleList = mutableListOf()
        peopleList.addAll(listOfPeople)
        /*notifyDataSetChanged()*/
    }

    class PeopleAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}