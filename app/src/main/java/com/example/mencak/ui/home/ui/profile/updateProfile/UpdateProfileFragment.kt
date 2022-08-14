package com.example.mencak.ui.home.ui.profile.updateProfile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mencak.R
import com.example.mencak.databinding.FragmentHomeBinding
import com.example.mencak.databinding.FragmentUpdateProfileBinding
import com.example.mencak.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class UpdateProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    var photo_location: Uri? = null
    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        val email = view?.findViewById<EditText>(R.id.emailEditTextProfile)
        val btnLogout = view?.findViewById<AppCompatButton>(R.id.logoutButton)

        email?.setText(currentUser?.email)
        binding.nameEditText.setText(currentUser?.displayName)

        btnLogout?.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.profilePhoto.setOnClickListener {
            findPhoto()
        }

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(binding.nameEditText.text.toString())
            .build()

        binding.updateProfileButton.setOnClickListener {
            if (binding.nameEditText.text!!.isEmpty()) {
                binding.nameEditText.error = "Please fill full name"
            }else if(binding.emailEditTextProfile.text!!.isEmpty()) {
                binding.emailEditTextProfile.error = "Please fill full name"
            }else if(binding.passwordEditTextProfile.text!!.isEmpty()) {
                binding.passwordEditTextProfile.error = "Please fill full name"
            }else {
                currentUser?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("UpdateProfileFragment", "User profile updated.")
                        }
                    }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            photo_location = data.data
            Glide.with(requireContext())
                .load(photo_location)
                .circleCrop()
                .into(binding.profilePhoto)
        }
    }

    private fun findPhoto() {
        val pic = Intent()
        pic.type = "image/*"
        pic.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(pic, 1)
    }
}