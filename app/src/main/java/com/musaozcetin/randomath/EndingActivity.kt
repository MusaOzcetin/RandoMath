package com.musaozcetin.randomath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.musaozcetin.randomath.databinding.ActivityEndingBinding

class EndingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEndingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val correctAnswers = intent.getIntExtra(Constants.countCorrect,0)
        binding.scoreText.text = "Your Score: $correctAnswers / 12"

        binding.tryAgainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}