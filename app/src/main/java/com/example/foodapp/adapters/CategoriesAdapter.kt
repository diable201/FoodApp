package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.CategoryItemBinding
import com.example.foodapp.pojo.Category
import com.example.foodapp.pojo.CategoryList

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoriesList = ArrayList<Category>()

    fun setCategoryList(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = categoriesList[position].strCategory
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

}