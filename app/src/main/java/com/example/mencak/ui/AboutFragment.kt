package com.example.mencak.ui

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mencak.R
import com.example.mencak.databinding.FragmentAboutBinding
import com.example.mencak.databinding.FragmentProfileBinding

class AboutFragment : DialogFragment() {

    private var _binding: FragmentAboutBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val percent = 90f / 100
            val dm = Resources.getSystem().displayMetrics
            val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
            val percentWidth = rect.width() * percent
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            it.window?.setLayout(percentWidth.toInt(), height)
            it.window?.setBackgroundDrawable(resources.getDrawable(R.drawable.border_dialog))
        }
        Glide.with(requireContext())
            .load(resources.getDrawable(R.drawable.image_md_1))
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.aboutImg1)
        Glide.with(requireContext())
            .load(resources.getDrawable(R.drawable.image_ml1))
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.aboutImg2)
        Glide.with(requireContext())
            .load(resources.getDrawable(R.drawable.image_cc1))
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.aboutImg3)
        Glide.with(requireContext())
            .load(resources.getDrawable(R.drawable.image_md_2))
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.aboutImg4)
        Glide.with(requireContext())
            .load(resources.getDrawable(R.drawable.image_ml2))
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.aboutImg5)
        Glide.with(requireContext())
            .load(resources.getDrawable(R.drawable.image_cc2))
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.aboutImg6)
    }
}