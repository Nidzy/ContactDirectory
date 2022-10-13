package com.example.directory.rooms.repository

import com.example.directory.apihelper.ApiConfig
import com.example.directory.apihelper.ApiService

class RoomRepository {
    suspend fun fetchRoom() =
        ApiConfig.getInstance().create(ApiService::class.java).getAllRooms()
}