package com.example.directory.people.repository

import com.example.directory.apihelper.ApiConfig
import com.example.directory.apihelper.ApiService

class PeopleRepository {
     suspend fun fetchPeople() =
        ApiConfig.getInstance().create(ApiService::class.java).getAllPeople()
}