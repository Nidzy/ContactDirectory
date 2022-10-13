package com.example.directory.rooms.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.directory.rooms.model.Rooms
import com.example.directory.rooms.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class RoomViewModel : ViewModel() {

    private val repository =RoomRepository()
    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.IO + job

    fun getAllRooms() : LiveData<ArrayList<Rooms>>{

        return liveData(context = coroutineContext) {
            val response = repository.fetchRoom()
            if (response.isSuccessful && response.body() != null) {
                // Log.d("TAG", "Success1")
                emit(response.body()!!)
                //Log.d("TAG", "Success2")
            } else {
                Log.d("TAG", "Failed")
            }
        }
    }
}