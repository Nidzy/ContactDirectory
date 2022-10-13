package com.example.directory

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.directory.databinding.ActivityMainBinding
import com.example.directory.people.adapter.PeopleAdapter
import com.example.directory.people.viewmodel.PeopleViewModel
import com.example.directory.rooms.adapter.RoomAdapter
import com.example.directory.rooms.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var peopleAdapter: PeopleAdapter
    private lateinit var roomAdapter: RoomAdapter
    lateinit var peopleViewModel: PeopleViewModel
    lateinit var roomViewModel: RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        peopleViewModel = ViewModelProvider(this).get(PeopleViewModel::class.java)
        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        rvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            setItemViewCacheSize(0)
        }
        rvRoomList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            isNestedScrollingEnabled = true
            setHasFixedSize(true)
            setItemViewCacheSize(0)
        }

        peopleAdapter = PeopleAdapter()
        roomAdapter = RoomAdapter()
        rvList.adapter = peopleAdapter
        rvRoomList.adapter = roomAdapter
       /* rvRoomList.visibility = View.GONE
        rvList.visibility = View.VISIBLE*/

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.people -> {
                    if (!isConnectedToIntenet()) {
                        showAlertForInternet(getString(R.string.warning_no_internet))
                    } else {
                        rvRoomList.visibility = View.GONE
                        rvList.visibility = View.VISIBLE
                        initPeopleData()


                    }
                    // Respond to navigation item 1 click
                    true
                }
                R.id.room -> {
                    // Respond to navigation item 2 click
                    if (!isConnectedToIntenet()) {
                        showAlertForInternet(getString(R.string.warning_no_internet))
                    } else {
                        rvList.visibility = View.INVISIBLE
                        rvRoomList.visibility = View.VISIBLE
                        initRoomData()
                    }
                    true
                }
                else -> false
            }
        }

        setSupportActionBar(binding.toolbar)

    }

    override fun onResume() {
        super.onResume()
        if (!isConnectedToIntenet()) {
            showAlertForInternet(getString(R.string.warning_no_internet))
        } else {
            rvRoomList.visibility = View.GONE
            rvList.visibility = View.VISIBLE
            initPeopleData()
        }
    }

    private fun initPeopleData() {
        progressBar.visibility = View.VISIBLE
        peopleViewModel.getAllPeople()
            .observe(this, Observer { peopleData ->
                if (peopleData != null) {
                    progressBar.visibility = View.GONE
                    //rvList.visibility = View.VISIBLE
                    peopleAdapter.addData(peopleData)
                    peopleAdapter.notifyDataSetChanged()

                }
            })
    }

    private fun initRoomData() {
        progressBar.visibility = View.VISIBLE
        roomViewModel.getAllRooms()
            .observe(this, Observer { roomData ->
                if (roomData != null) {
                    progressBar.visibility = View.GONE
                    //rvList.visibility = View.GONE
                    //rvRoomList.visibility = View.VISIBLE
                    roomAdapter.addData(roomData)
                    //roomAdapter.notifyDataSetChanged()

                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}