package com.example.directory.people.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.directory.people.model.PeopleDataResponse
import com.example.directory.people.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class PeopleViewModel : ViewModel() {

    private val repository =PeopleRepository()
    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.IO + job

    fun getAllPeople() : LiveData<ArrayList<PeopleDataResponse>>{

        return liveData(context = coroutineContext) {
            val response = repository.fetchPeople()
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

