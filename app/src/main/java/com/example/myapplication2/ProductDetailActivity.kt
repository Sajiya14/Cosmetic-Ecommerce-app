package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        val imageView: ImageView = findViewById(R.id.product_image)
        val textView1: TextView = findViewById(R.id.product_name)
        val textView2: TextView = findViewById(R.id.product_price)

        val bundle: Bundle?= intent.extras
        val img = bundle!!.getInt("imgId")
        val name = bundle.getString("Name")
        val price = bundle.getString("Price")

        imageView.setImageResource(img)
        textView1.text = name
        textView2.text = price
    }
}