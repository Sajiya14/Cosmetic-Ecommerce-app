package com.example.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var etPassword: EditText
    private lateinit var ResetBtn: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        etPassword = findViewById(R.id.etEmail)
        ResetBtn = findViewById(R.id.ResetBtn)

        auth = FirebaseAuth.getInstance()

        ResetBtn.setOnClickListener {
            val sPassword = etPassword.text.toString()
            auth.sendPasswordResetEmail(sPassword)
                .addOnSuccessListener {
                    Toast.makeText(this,"Please Check your Email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }



}