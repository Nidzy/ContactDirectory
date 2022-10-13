package com.example.directory.people.model

data class PeopleDataResponse (
    val createdAt: String,
    val firstName: String,
    val avatar: String,
    val lastName: String,
    val email: String,
    val jobtitle: String,
    val favouriteColor: String,
    val id: String,
    val data: Data? = null,
    val to: String? = null,
    val fromName: String? = null
    )

