package com.lazzy.legendemyu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlayer: RecyclerView
    private val list = ArrayList<Player>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlayer = findViewById(R.id.rv_cars)
        rvPlayer.setHasFixedSize(true)

        list.addAll(getListPlayers())
        showRecyclerList()
    }

    private fun getListPlayers(): ArrayList<Player> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listPlayer = ArrayList<Player>()
        for (i in dataName.indices) {
            val hero = Player(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listPlayer.add(hero)
        }
        return listPlayer
    }
    private fun showRecyclerList() {
        rvPlayer.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListPlayerAdapter(list)
        rvPlayer.adapter = listHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvPlayer.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvPlayer.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}