package com.vlyashuk.rainbowgoldstation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vlyashuk.rainbowgoldstation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        MAIN = this
        checkFirstStart()
    }

    private fun checkFirstStart() {

        val sp = getSharedPreferences("hasVisited", MODE_PRIVATE)
        val hasVisited = sp.getBoolean("hasVisited", false)
        if (!hasVisited) {

            val e = sp.edit()
            e.putBoolean("hasVisited", true)
            e.apply()
            startMainFragment()
        } else {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, WebViewFragment())
                .commit()
        }
    }

    private fun startMainFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host_fragment, MainFragment())
            .commit()
    }
}