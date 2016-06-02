package com.takusemba.dribbblesampleapp.adapter

import android.annotation.TargetApi
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.squareup.picasso.Picasso
import com.takusemba.dribbblesampleapp.R
import com.takusemba.dribbblesampleapp.model.Shot
import com.takusemba.dribbblesampleapp.screen.DetailActivity

import java.util.ArrayList

/**
 * Created by takusemba on 15/11/09.
 */
class GridRecycleViewAdapter(private val mContext: Context) : RecyclerView.Adapter<GridRecycleViewAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater
    private val mShots: MutableList<Shot>?

    private val SHOT = "shot"
    private val SHARED_ELEMENT = "shared_element"

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imageview: ImageView

        init {
            imageview = v.findViewById(R.id.item_image) as ImageView
        }
    }

    init {
        mShots = ArrayList<Shot>()
        mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun setShots(shots: List<Shot>) {
        mShots!!.clear()
        for (shot in shots) {
            mShots.add(shot)
        }
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = mInflater.inflate(R.layout.item_image, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shot = mShots!![position]

        if (!TextUtils.isEmpty(shot.image?.normal)) {
            loadImage(shot.image?.normal as String, holder)
        } else if (!TextUtils.isEmpty(shot.image?.teaser)) {
            loadImage(shot.image?.teaser as String, holder)
        }
        holder.imageview.setOnClickListener{
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra(SHOT, shot)
            val options = ActivityOptions.makeSceneTransitionAnimation(mContext as Activity, holder.imageview, SHARED_ELEMENT)
            mContext.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return mShots?.size ?: 0
    }

    fun loadImage(image: String, holder: ViewHolder) {
        Picasso.with(mContext).load(image).into(holder.imageview)
    }
}
