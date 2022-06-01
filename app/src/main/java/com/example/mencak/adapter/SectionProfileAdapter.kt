package com.example.mencak.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mencak.ui.home.ui.profile.history.HistoryFragment
import com.example.mencak.ui.home.ui.profile.updateProfile.UpdateProfileFragment

class SectionProfileAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Profile"
            1 -> "History Food"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0 -> {
                fragment = UpdateProfileFragment()
                return  fragment
            }
            1-> {
                fragment = HistoryFragment()
                return fragment
            }
            else -> {
                fragment = UpdateProfileFragment()
                return  fragment
            }
        }
    }

}