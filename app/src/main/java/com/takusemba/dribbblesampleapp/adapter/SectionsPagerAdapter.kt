package com.takusemba.dribbblesampleapp.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.takusemba.dribbblesampleapp.network.ShotApi
import com.takusemba.dribbblesampleapp.screen.DesignGridFragment

import java.util.ArrayList

/**
 * Created by takusemba on 15/11/09.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val SECTION_NUMBER = "section_number"
    private val mFragments = ArrayList<Fragment>()

    init {
        for (i in 0..count - 1) {
            mFragments.add(DesignGridFragment())
        }

    }

    override fun getItem(position: Int): Fragment {
        val args = Bundle()
        args.putInt(SECTION_NUMBER, position)
        val fragment = mFragments[position]
        fragment.arguments = args
        return fragment
    }

    override fun getCount(): Int {
        return 6
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return ShotApi.Type.ANIMATED.key
            1 -> return ShotApi.Type.ATTACHMENTS.key
            2 -> return ShotApi.Type.DEBUTS.key
            3 -> return ShotApi.Type.PLAYOFFS.key
            4 -> return ShotApi.Type.REBOUNDS.key
            5 -> return ShotApi.Type.TEAMS.key
        }
        return null
    }

}