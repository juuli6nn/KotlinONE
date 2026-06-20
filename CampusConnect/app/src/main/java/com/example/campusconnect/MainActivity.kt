package com.example.campusconnect

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campusconnect.databinding.ActivityMainBinding
import android.os.Build
import android.app.NotificationManager
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.view.MenuItem
import androidx.core.app.NotificationCompat
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Create binding object
    private lateinit var binding: ActivityMainBinding

    // Create notification variables
    private val channelId = "Demo_Channel"
    private var nofiticationId = 1
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()
    ) {
        granted -> if (granted) sendNotification()
        else Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)

        // set the binding object as the layout
        enableEdgeToEdge()
        setContentView(binding.root)

        // Set the toolbar as the action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Campus Connect"

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.bn_home -> HomeFragment()
                R.id.bn_dashboard -> DashboardFragment()
                R.id.bn_notifications -> NotificationsFragment()
                else -> HomeFragment()
            }
            // cREATE THE fragment support
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()

            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set the default fragment when app loads
        if (savedInstanceState == null) binding.bottomNavigation.selectedItemId = R.id.bn_home
    }

    // Inflate the toolbar with the main menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Use the menu inflater to inflate the menu
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.mn_settings -> {
                Toast.makeText(
                    this,
                    "Settings menu is selected",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            R.id.mn_about -> {
                Toast.makeText(
                    this,
                    "About menu is selected",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            R.id.mn_notify -> {
                checkPermissionAndNotify()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    // create notification helper functions
    private fun createNotificationChannel() {
        // Check the android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Demo Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This is a demo notification bobo Turkey Arda Dumb Guler"
            }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    // helper function to check if permission is granted
    private fun checkPermissionAndNotify() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            ){
            requestPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS)
        } else {
            sendNotification()
        }
    }
    // Send the notification
    private fun sendNotification() {
        // Create a notification object
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("ARDA FOOLER SOLD PARLAY")
            .setContentText("This is a notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        getSystemService(NotificationManager::class.java).notify(nofiticationId++, notification)
    }
}