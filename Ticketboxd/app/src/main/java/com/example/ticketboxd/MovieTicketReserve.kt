package com.example.ticketboxd

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MovieTicketReserve : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_ticket_reserve)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etMovieTitle = findViewById<EditText>(R.id.etMovieTitle)
        val etCustomerName = findViewById<EditText>(R.id.etCustomerName)
        val etTicketCount = findViewById<EditText>(R.id.etTicketCount)
        val btnReserve = findViewById<Button>(R.id.btnReserve)
        val tvSummary = findViewById<TextView>(R.id.tvSummary)

        btnReserve.setOnClickListener {
            val movieTitle = etMovieTitle.text.toString()
            val customerName = etCustomerName.text.toString()
            val ticketCount = etTicketCount.text.toString()

            if (movieTitle.isNotBlank() && customerName.isNotBlank() && ticketCount.isNotBlank()) {
                val summary = "Reservation Summary:\n\n" +
                        "Movie: $movieTitle\n" +
                        "Customer: $customerName\n" +
                        "Tickets: $ticketCount"
                tvSummary.text = summary
            } else {
                tvSummary.text = "Error: Please fill in all fields."
            }
        }
    }
}