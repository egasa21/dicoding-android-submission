package com.lazzy.legendemyu

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class DetailActivity : AppCompatActivity() {

    companion object {
        const val Key_Player = "Key_Player"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar: ActionBar? = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val dataPlayer = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Player>(Key_Player, Player::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Player>(Key_Player)
        }

        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription : TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto : ImageView = findViewById(R.id.iv_detail_photo)

        tvDetailName.text = dataPlayer?.name
        tvDetailDescription.text = dataPlayer?.description
        if (dataPlayer != null) {
            ivDetailPhoto.setImageResource(dataPlayer.photo)
        }

        val btnShare: Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${dataPlayer?.name.toString()}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}