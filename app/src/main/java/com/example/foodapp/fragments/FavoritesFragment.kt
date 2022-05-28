package com.example.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.activities.MainActivity
import com.example.foodapp.activities.MealActivity
import com.example.foodapp.adapters.MealAdapter
import com.example.foodapp.databinding.FragmentFavoritesBinding
import com.example.foodapp.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var favouritesAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        observeFavourites()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedMeal = favouritesAdapter.differ.currentList[position]
                viewModel.deleteMeal(deletedMeal)
                Snackbar.make(requireView(), "Meal deleted", Snackbar.LENGTH_LONG).apply {
                    setAction(
                        "Undo",
                        View.OnClickListener {
                            viewModel.insertMeal(deletedMeal)
                        })
                }.show()
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavourites)
    }

    private fun prepareRecyclerView() {
        favouritesAdapter = MealAdapter()
        favouritesAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(HomeFragment.MEAL_ID, meal.idMeal)
            intent.putExtra(HomeFragment.MEAL_NAME, meal.strMeal)
            intent.putExtra(HomeFragment.MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
        binding.rvFavourites.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = favouritesAdapter
        }
    }

    private fun observeFavourites() {
        viewModel.observeFavouritesMealsLiveData().observe(requireActivity() , Observer { meals ->
            Log.e("test", meals.toString())
            favouritesAdapter.differ.submitList(meals)
        })
    }

}