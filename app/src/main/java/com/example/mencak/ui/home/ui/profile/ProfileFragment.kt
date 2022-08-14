package com.example.mencak.ui.home.ui.profile

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mencak.R
import com.example.mencak.adapter.SectionProfileAdapter
import com.example.mencak.databinding.FragmentHomeBinding
import com.example.mencak.databinding.FragmentProfileBinding
import com.example.mencak.ui.AboutFragment
import com.example.mencak.ui.home.ui.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sectionPagerAdapter = SectionProfileAdapter(
            childFragmentManager
        )
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        Glide.with(requireContext())
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6zes53m4a_2VLTcmTn_bHk8NO5SkuWfcQbg&usqp=CAU")
            .circleCrop()
            .into(binding.ivProfil)

        binding.iconInfo.setOnClickListener {
           var dialog = AboutFragment()
            dialog.showsDialog
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}