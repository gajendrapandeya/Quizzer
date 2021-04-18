package com.codermonkeys.quizzer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var etUserName: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btn_start)
        etUserName = findViewById(R.id.et_name)

        btnStart.setOnClickListener {
            if(etUserName.text.toString().isEmpty())
                Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show()
            else {
                val quizIntent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etUserName.text.toString())
                startActivity(quizIntent)
                finish()
            }
        }
    }
}