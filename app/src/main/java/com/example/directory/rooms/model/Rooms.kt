package com.example.directory.rooms.model

data class Rooms(
    val createdAt: String,
    val isOccupied: Boolean,
    val maxOccupancy: Long,
    val id: String
)