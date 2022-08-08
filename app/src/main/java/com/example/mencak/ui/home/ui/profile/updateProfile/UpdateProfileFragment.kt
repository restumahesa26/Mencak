package com.example.mencak.ui.home.ui.profile.updateProfile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.example.mencak.R
import com.example.mencak.ui.home.HomeActivity
import com.example.mencak.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class UpdateProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        val email = view?.findViewById<EditText>(R.id.emailEditTextProfile)
        val btnLogout = view?.findViewById<AppCompatButton>(R.id.logoutButton)

        email?.setText(currentUser?.email)

        btnLogout?.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}