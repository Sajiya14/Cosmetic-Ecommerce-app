package com.example.myapplication2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var ForgetPassword: TextView
    private lateinit var register: TextView
    private lateinit var signin: Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()



        //register button
        register = findViewById(R.id.TvRegister)

        register.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        //froget password button
        ForgetPassword = findViewById(R.id.ForgetPassword)

        ForgetPassword.setOnClickListener {

            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }


        //Signin button
        signin = findViewById(R.id.SignInButton)

        signin.setOnClickListener {

            val email = findViewById<EditText>(R.id.SignInEmail)
            val pass = findViewById<EditText>(R.id.SignInPassword)

            val email1 = email.text.toString()
            val password = pass.text.toString()

            if (email.text.isEmpty() || pass.text.isEmpty()){
                Toast.makeText(baseContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email1, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "success",
                            Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
        }
    }

}