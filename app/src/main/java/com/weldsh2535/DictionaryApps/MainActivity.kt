package com.weldsh2535.DictionaryApps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.weldsh2535.DictionaryApps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigationView()
        loadFragment(SearchFragment())
    }

  private fun  setupBottomNavigationView(){
      setupClickListener()
       binding.botomNavigationView.getOrCreateBadge(R.id.bookmark)
      binding.botomNavigationView.getBadge(R.id.bookmark)?.apply {
          number = 10
      }
    }
    private fun setupClickListener(){
        binding.botomNavigationView.setOnItemSelectedListener {
            val fragment = when(it.itemId){
                R.id.about -> {AboutFragment()}
                R.id.bookmark -> {
                    removeBadge(it.itemId)
                    BookMarksFragment()}
                else -> { SearchFragment() }
            }
            loadFragment(fragment)
            true
        }
    }
    private fun loadFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
    private fun removeBadge(badgeId:Int){
        binding.botomNavigationView.getBadge(badgeId)?.let { badgeDrawer ->
            if (badgeDrawer.isVisible){
                binding.botomNavigationView.removeBadge(badgeId)
            }
        }
    }
}