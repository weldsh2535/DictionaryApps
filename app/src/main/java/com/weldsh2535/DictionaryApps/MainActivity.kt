package com.weldsh2535.DictionaryApps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.weldsh2535.DictionaryApps.Activity.AboutActivity
import com.weldsh2535.DictionaryApps.Activity.BookMarksActivity
import com.weldsh2535.DictionaryApps.Activity.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.my_drawer_layout)

        menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigation.setNavigationItemSelectedListener { item ->
            when(item?.itemId){
                R.id.menuSearch ->{
                    val intent = Intent(this@MainActivity,SearchActivity::class.java)
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.menuBookmark -> {
                    val intent = Intent(this@MainActivity,BookMarksActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.menuAbout -> {
                    val intent = Intent(this@MainActivity,AboutActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
        btn_geez_amharic.setOnClickListener {
            var intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.pop_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        }else super.onOptionsItemSelected(item)
    }

}