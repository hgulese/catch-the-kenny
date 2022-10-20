package com.huseyinilker.catchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.huseyinilker.catchthekenny.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler: Handler = Handler(Looper.getMainLooper())
    var runnable = Runnable { }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Image Array(hide)
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()
        moveKenny()

        // CountDownTimer
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {

                binding.timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)
                hideImages()

                // Alert Dialog
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Time over!")
                alert.setMessage("Play again?")
                alert.setPositiveButton("Yes") {dialog, which ->
                    //Restart
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(applicationContext, "GAME OVER!!!", Toast.LENGTH_LONG).show()
                }
                alert.show()

            }

        }.start()

    }

    fun hideImages() {

        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }

    }

    fun moveKenny() {
        runnable = object : Runnable {
            override fun run() {

                hideImages()

                val random = Random()
                val rnds = random.nextInt(9)

                imageArray[rnds].visibility = View.VISIBLE

                handler.postDelayed(runnable, 400)
            }
        }

        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score++
        binding.scoreText.text = "Score: $score"
    }

}