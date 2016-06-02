package com.takusemba.dribbblesampleapp.screen

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.takusemba.dribbblesampleapp.R
import com.takusemba.dribbblesampleapp.model.Artist
import com.takusemba.dribbblesampleapp.model.Shot
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.activity_detail.*

/**
 * Created by takusemba on 15/11/09.
 */
class DetailActivity : AppCompatActivity() {

    private var mShot: Shot? = null

    private var mImageView: ImageView? = null

    private var mAvatar: CircleImageView? = null

    private val SHOT = "shot"

    private val SHARED_ELEMENT = "shared_element"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (intent.extras.containsKey(SHOT)) {
            mShot = intent.extras.getSerializable(SHOT) as Shot?
        }
        setTextViews()
        setImageViews()
        setTransition()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setTransition() {
        mImageView?.transitionName = SHARED_ELEMENT
    }

    private fun setImageViews() {
        mImageView = findViewById(R.id.detail_image) as ImageView
        mAvatar = findViewById(R.id.avatar) as CircleImageView
        Picasso.with(this).load(mShot?.image?.normal).into(detail_image)
        Picasso.with(this).load(mShot?.artist?.avatarUrl).into(mAvatar)
    }

    private fun setTextViews() {
        title.text = mShot?.title
        description.text = mShot?.description
        var artist: Artist? = mShot?.artist
        name.text = artist?.name
        follower.text = artist?.followersCount.toString()
        following.text = artist?.followingsCount.toString()
        shots_count.text = artist?.likesReceivedCount.toString()
        likes_received_count.text = artist?.likesReceivedCount.toString()
        comments_received_count.text = artist?.commentsReceivedCount.toString()
    }
}
