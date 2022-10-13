package com.example.directory.apihelper

import com.example.directory.people.model.PeopleDataResponse
import com.example.directory.rooms.model.Rooms
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("people")
    suspend fun getAllPeople(): Response<ArrayList<PeopleDataResponse>>

    @GET("rooms")
    suspend fun getAllRooms(): Response<ArrayList<Rooms>>

}