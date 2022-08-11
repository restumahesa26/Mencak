package com.example.mencak.ui.reviewfood

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowInsets
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mencak.R
import com.example.mencak.databinding.ActivityReviewFoodBinding
import com.example.mencak.model.FoodModel

class ReviewFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<FoodModel>("DATA")
        Glide.with(applicationContext)
            .load(data?.image)
            .transforms(CenterCrop(), RoundedCorners(24))
            .into(binding.ivPoster)

        getSupportActionBar()?.hide()
        setupView()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}