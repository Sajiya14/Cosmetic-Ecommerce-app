package com.example.myapplication2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private var productList: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun onItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent,false)
        return ProductViewHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int,) {

        holder.productImage.setImageResource(productList[position].image)
        holder.productName.text = productList[position].name
        holder.productPrice.text = productList[position].price
    }


    class ProductViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val productImage:ImageView = itemView.findViewById(R.id.product_image)
        val productName:TextView = itemView.findViewById(R.id.product_name)
        val productPrice:TextView = itemView.findViewById(R.id.product_price)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }



    }


}

