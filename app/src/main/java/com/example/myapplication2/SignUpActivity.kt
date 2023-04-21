package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class SignUpActivity : AppCompatActivity(){


    private lateinit var auth: FirebaseAuth
    private lateinit var SignUpEmail: EditText
    private lateinit var SignUpPassword: EditText
    private lateinit var etName: EditText
    private lateinit var rePassword: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        SignUpEmail = findViewById(R.id.SignUpEmail)
        SignUpPassword = findViewById(R.id.SignUpPassword)
        etName = findViewById(R.id.etName)
        rePassword = findViewById(R.id.rePassword)
        val signUpButton = findViewById<Button>(R.id.signUp_btn)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val login = findViewById<TextView>(R.id.TvLogin)

        login.setOnClickListener {

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }



        signUpButton.setOnClickListener {


            val email = SignUpEmail.text.toString()
            val password = SignUpPassword.text.toString()
            val repassword = rePassword.text.toString()
            val name = etName.text.toString()

            if (email.isEmpty() || password.isEmpty() || repassword.isEmpty() || name.isEmpty())       {
                Toast.makeText(baseContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener


            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "success", Toast.LENGTH_SHORT).show()



                        auth = Firebase.auth

                        val user = auth.currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(name) // Set the name
                            .build()
                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Name updated successfully
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    // Name update failed
                                }
                            }

                        val userUpdates = hashMapOf<String, Any>(
                            "Name" to name,
                            "Email" to email,
                            "Password" to password
                        )
                        val db = FirebaseFirestore.getInstance()

                        db.collection("users").document(user?.uid.toString())
                            .set(userUpdates)
                            .addOnSuccessListener {
                                // Phone number added successfully
                            }
                            .addOnFailureListener {
                                // Phone number addition failed
                            }

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show()
                }


        }


    }
}