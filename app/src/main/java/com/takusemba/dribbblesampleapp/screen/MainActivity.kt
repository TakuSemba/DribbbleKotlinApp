package com.takusemba.dribbblesampleapp.screen

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.takusemba.dribbblesampleapp.R
import com.takusemba.dribbblesampleapp.adapter.SectionsPagerAdapter

/**
 * Created by takusemba on 15/11/09.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        var mViewPager: ViewPager = findViewById(R.id.pager) as ViewPager
        var mTabLayout: TabLayout = findViewById(R.id.tabs) as TabLayout

        var mSectionsPagerAdapter: SectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mSectionsPagerAdapter
        mViewPager.offscreenPageLimit = mSectionsPagerAdapter.count
        mTabLayout.setupWithViewPager(mViewPager)

    }
}
