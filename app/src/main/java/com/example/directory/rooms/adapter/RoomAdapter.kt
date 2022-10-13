package com.example.directory.rooms.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R
import com.example.directory.rooms.model.Rooms
import kotlinx.android.synthetic.main.row_room.view.*

class RoomAdapter(var roomList: MutableList<Rooms> = mutableListOf()) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_room, parent, false)
        return RoomViewHolder(view)
    }


    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {

        if (roomList.size > 0) {
            val room = roomList[position]
            holder.itemView.tvRoomId.text = "Room Id: " + room.id
            holder.itemView.tvRoomCapacity.text = "MaxOccupancy of Room: " + room.maxOccupancy.toString()
            if (room.isOccupied) {
                holder.itemView.tvRoom.text = "Room Occupied"
                holder.itemView.tvRoom.setTextColor(Color.RED)

            } else {
                holder.itemView.tvRoom.text = "Room Available"
                holder.itemView.tvRoom.setTextColor(Color.GREEN)

            }
        }
    }

    fun addData(listOfRoom: List<Rooms>) {
        roomList = mutableListOf()
        roomList.addAll(listOfRoom)
        /*notifyDataSetChanged()*/
    }

    class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}