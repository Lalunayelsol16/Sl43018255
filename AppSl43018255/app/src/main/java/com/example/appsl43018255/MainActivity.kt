package com.example.appsl43018255

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.appsl48399119.R
import com.example.appsl48399119.databinding.ActivityMainBinding
import com.example.appsl43018255.ui.gallery.GalleryFragment
import com.example.appsl43018255.ui.home.HomeFragment

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        supportActionBar!!.setDisplayShowTitleEnabled(false)


        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, HomeFragment.newInstance())
            .addToBackStack(GalleryFragment::class.java.name)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        var myFragment: Fragment? = null
        var fragmentSelected = false

        when (id) {
            R.id.nav_gallery -> {
                myFragment = GalleryFragment()
                fragmentSelected = true
            }
            R.id.nav_home -> {
                myFragment = HomeFragment()
                fragmentSelected = true
            }

        }

        if (fragmentSelected) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, myFragment!!)
                .addToBackStack(fragmentSelected::class.java.name)
                .commit()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}