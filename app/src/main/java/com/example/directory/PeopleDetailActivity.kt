package com.example.directory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.directory.databinding.ActivityPeopleDetailBinding
import com.squareup.picasso.Picasso

class PeopleDetailActivity : AppCompatActivity() {

    lateinit var intentData: Intent
    private lateinit var binding: ActivityPeopleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPeopleDetailBinding.inflate(layoutInflater)

        supportActionBar?.setHomeButtonEnabled(true)
        setContentView(binding.root)

        intentData = intent

        Picasso.with(applicationContext).load(intentData.getStringExtra("avtar")).into(binding.ivAvtar)
        binding.tvFirstName.text = "FirstName: " +  intentData.getStringExtra("firstName")
        binding.tvLastName.text  = "LastName: "+ intentData.getStringExtra("lastName")
        binding.tvEmail.text  =  "Email: "+intentData.getStringExtra("email")
        binding.tvJobTitle.text  = "JobTitle: "+ intentData.getStringExtra("jobTitle")
        binding.tvFavouriteColor.text  ="FavouriteColor: "+ intentData.getStringExtra("favouriteColor")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}