package com.example.esportgames

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.esportgames.databinding.ActivityMainBinding
import com.example.esportgames.util.SharedPrefManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var authContainer: FragmentContainerView
    private lateinit var mainContainer: FragmentContainerView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefManager: SharedPrefManager
    private val navGraphs = mapOf(
        R.id.home_menu_fragment to R.navigation.home_nav_graph,
        R.id.match_menu_fragment to R.navigation.match_nav_graph,
        R.id.result_menu_fragment to R.navigation.result_nav_graph,
        R.id.profile_menu_fragment to R.navigation.profile_nav_graph
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefManager = SharedPrefManager(this)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        authContainer = binding.authHostContainer
        mainContainer = binding.authHostContainer
        binding.mainHostContainer.visibility = View.GONE
        binding.authHostContainer.visibility = View.VISIBLE
        binding.bottomNavigation.visibility = View.GONE
        setupBottomNavigation()

        sharedPrefManager.isLoggedIn.observe(this) { loggedIn ->
            if (loggedIn) {
                showMainContainer()
            } else {
                showAuthContainer()
            }
        }

    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val graphResId = navGraphs[item.itemId]
            graphResId?.let {
                navController.setGraph(it)
                true
            } ?: false
        }
    }

    fun showMainContainer() {
        navHostFragment = supportFragmentManager.findFragmentById(binding.mainHostContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        binding.authHostContainer.visibility = View.GONE
        binding.mainHostContainer.visibility = View.VISIBLE
        binding.bottomNavigation.visibility = View.VISIBLE

        navHostFragment.navController.popBackStack(navHostFragment.navController.graph.startDestinationId, false)
    }

    fun showAuthContainer() {
        navHostFragment = supportFragmentManager.findFragmentById(binding.authHostContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        navHostFragment.navController.popBackStack(navHostFragment.navController.graph.startDestinationId, false)
        binding.authHostContainer.visibility = View.VISIBLE
        binding.mainHostContainer.visibility = View.GONE
        binding.bottomNavigation.visibility = View.GONE
    }

}