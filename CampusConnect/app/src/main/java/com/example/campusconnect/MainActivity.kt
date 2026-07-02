package com.example.campusconnect

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campusconnect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val channelId = "registration_channel"
    private var notificationId = 1
    private var pendingNotificationTitle = "Campus Connect"
    private var pendingNotificationText = "Registration successful."

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                sendNotification(pendingNotificationTitle, pendingNotificationText)
            } else {
                Toast.makeText(this, pendingNotificationText, Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Campus Connect"

        createNotificationChannel()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.bn_home -> HomeFragment()
                R.id.bn_members -> DashboardFragment()
                R.id.bn_registration -> NotificationFragment()
                else -> HomeFragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()

            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.bn_home
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.mn_light_mode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
            R.id.mn_dark_mode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true
            }
            R.id.mn_exit -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Registration",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Registration result notifications"
            }

            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    fun showRegistrationSuccessNotification() {
        val title = getString(R.string.app_name)
        val text = getString(R.string.registration_success)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        checkPermissionAndNotify(title, text)
    }

    private fun checkPermissionAndNotify(title: String, text: String) {
        pendingNotificationTitle = title
        pendingNotificationText = text

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            sendNotification(title, text)
        }
    }

    private fun sendNotification(title: String, text: String) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_register)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        getSystemService(NotificationManager::class.java).notify(notificationId++, notification)
    }
}
