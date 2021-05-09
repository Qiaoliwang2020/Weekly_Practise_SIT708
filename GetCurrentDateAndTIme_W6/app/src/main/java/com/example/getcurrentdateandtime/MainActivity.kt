package com.example.getcurrentdateandtime

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val current = LocalDateTime.now()
        val time = findViewById<TextView>(R.id.time)
        time.text = "Current Date and Time is " + current.toString()

        val formatTime = findViewById<TextView>(R.id.formatTime)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)

        formatTime.text = formatted

        val formatTime1 = findViewById<TextView>(R.id.formatTime1)

        val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted1 = current.format(formatter1)

        formatTime1.text = formatted1


        val formatTime2 = findViewById<TextView>(R.id.formatTime2)

        val formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted2 = current.format(formatter2)

        formatTime2.text = formatted2

        val formatTime3 = findViewById<TextView>(R.id.formatTime3)
        val formatter3 = DateTimeFormatter.BASIC_ISO_DATE
        val formatted3 = current.format(formatter3)

        formatTime3.text = formatted3


        val formatTime4 = findViewById<TextView>(R.id.formatTime4)
        val formatter4 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted4 = current.format(formatter4)

        formatTime4.text = formatted4


        val formatTimeNextYear = findViewById<TextView>(R.id.formatTimeNextYear)

        val nextYear = current.plusYears(1)

        val formatterNextYear = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formattedNextYear = nextYear.format(formatterNextYear)

        formatTimeNextYear.text = "Next year " + formattedNextYear

    }



}