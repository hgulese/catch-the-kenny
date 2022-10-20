package com.huseyinilker.catchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.huseyinilker.catchthekenny.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // CountDownTimer
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {

                // Alert Dialog
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Time over!")
                alert.setMessage("Play again?")
                alert.setPositiveButton("Yes") {dialog, which ->
                    Toast.makeText(applicationContext, "yes", Toast.LENGTH_SHORT).show()
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(applicationContext, "no", Toast.LENGTH_SHORT).show()
                }
                alert.show()

            }

        }.start()

    }

    fun increaseScore(view: View) {
        score++
        binding.scoreText.text = "Score: $score"
    }

}