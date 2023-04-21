package com.example.myapplication2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: ArrayList<Product>
    private lateinit var productAdapter: ProductAdapter


    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        auth = Firebase.auth

        val User = auth.currentUser
        if (User != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(User.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val name21 = User.displayName

                        val userInfoTextView = findViewById<TextView>(R.id.name21)
                        userInfoTextView.text = "Welcome $name21"



                    }
                }
                .addOnFailureListener { exception ->
                    // Failed to retrieve user data

                    Toast.makeText(baseContext, "Failed to retrieve user data",
                        Toast.LENGTH_SHORT).show()
                }
        }

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,2 )

        productList = ArrayList()



        productList.add(Product(R.drawable.shello_cream,"Shello Body Lossion","Rs.570"))
        productList.add(Product(R.drawable.bio_glow,"Bio Glow Moisturising Cream","Rs.1,900.00"))
        productList.add(Product(R.drawable.vesline,"Vaseline Body Lotion Healthy White","Rs.500.00"))
        productList.add(Product(R.drawable.facial_scrub,"Beauty Skin Avocado Facial Scrub","Rs.2000.00"))
        productList.add(Product(R.drawable.evon_cream,"EVEN Cucumber Cleansing Cream","Rs.430.00"))
        productList.add(Product(R.drawable.ponds,"POND'S White Beauty Cream","Rs.1200.00"))
        productList.add(Product(R.drawable.herble,"HERBAL-Hair Oil","Rs.395.00"))
        productList.add(Product(R.drawable.face_wash,"PEPPERMINT FACIAL WASH","Rs.290.00"))
        productList.add(Product(R.drawable.shello_cream,"Shello Body Lossion","Rs.570"))
        productList.add(Product(R.drawable.bio_glow,"Bio Glow Moisturising Cream","Rs.1,900.00"))
        productList.add(Product(R.drawable.vesline,"Vaseline Body Lotion Healthy White","Rs.500.00"))
        productList.add(Product(R.drawable.facial_scrub,"Beauty Skin Avocado Facial Scrub","Rs.2000.00"))
        productList.add(Product(R.drawable.evon_cream,"EVEN Cucumber Cleansing Cream","Rs.430.00"))
        productList.add(Product(R.drawable.ponds,"POND'S White Beauty Cream","Rs.1200.00"))
        productList.add(Product(R.drawable.herble,"HERBAL-Hair Oil","Rs.395.00"))
        productList.add(Product(R.drawable.face_wash,"PEPPERMINT FACIAL WASH","Rs.290.00"))


        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter
        productAdapter.onItemClickListener(object : ProductAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
                intent.putExtra("imgId",productList[position].image)
                intent.putExtra("Name",productList[position].name)
                intent.putExtra("Price",productList[position].price)
                startActivity(intent)
            }
        })
    }
}