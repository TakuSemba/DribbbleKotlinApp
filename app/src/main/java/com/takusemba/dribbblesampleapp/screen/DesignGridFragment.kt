package com.takusemba.dribbblesampleapp.screen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.takusemba.dribbblesampleapp.R
import com.takusemba.dribbblesampleapp.adapter.GridRecycleViewAdapter
import com.takusemba.dribbblesampleapp.model.Shot
import com.takusemba.dribbblesampleapp.network.RestClient
import com.takusemba.dribbblesampleapp.network.ShotApi
import com.wang.avi.AVLoadingIndicatorView
import java.util.Random

import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

/**
 * Created by takusemba on 15/11/09.
 */
class DesignGridFragment : Fragment() {

    private var mGridRecycleViewAdapter: GridRecycleViewAdapter? = null

    private var mProgressBar: AVLoadingIndicatorView? = null

    private var mRecyclerView: RecyclerView? = null

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    private var mNumber: Int = 0

    private val SECTION_NUMBER = "section_number"

    private val COLUMNS = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNumber = arguments.getInt(SECTION_NUMBER)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_grid_design, container, false)

        bindViews(view)
        setRecycleView()
        setRefreshView()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchShots(Random().nextInt(10))
    }

    private fun bindViews(view: View) {
        mProgressBar = view.findViewById(R.id.loading_indicator) as AVLoadingIndicatorView
        mSwipeRefreshLayout = view.findViewById(R.id.refresh) as SwipeRefreshLayout
        mRecyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
    }

    private fun setRecycleView() {
        val mLayoutManager = GridLayoutManager(activity, COLUMNS)
        mRecyclerView?.layoutManager = mLayoutManager
        mGridRecycleViewAdapter = GridRecycleViewAdapter(activity)
        mRecyclerView?.adapter = mGridRecycleViewAdapter
        mRecyclerView?.setHasFixedSize(true)
    }

    private fun setRefreshView() {
        mSwipeRefreshLayout?.setColorSchemeResources(R.color.colorAccent)
        mSwipeRefreshLayout?.setOnRefreshListener({fetchShots(Random().nextInt(10))})
    }

    private fun fetchShots(page: Int) {

        val type: ShotApi.Type
        when (mNumber) {
            0 -> type = ShotApi.Type.ANIMATED
            1 -> type = ShotApi.Type.ATTACHMENTS
            2 -> type = ShotApi.Type.DEBUTS
            3 -> type = ShotApi.Type.PLAYOFFS
            4 -> type = ShotApi.Type.REBOUNDS
            5 -> type = ShotApi.Type.TEAMS
            else -> type = ShotApi.Type.ANIMATED
        }

        RestClient.restAdapter.create<ShotApi>(ShotApi::class.java).fetchShots(type.key, page, object : Callback<List<Shot>> {
            override fun success(shots: List<Shot>, response: Response) {
                if (isAdded && !isDetached) {
                    mProgressBar?.visibility = View.GONE
                    mGridRecycleViewAdapter?.setShots(shots)
                    mSwipeRefreshLayout?.isRefreshing = false
                }
            }

            override fun failure(error: RetrofitError) {
                mProgressBar?.visibility = View.GONE
                mSwipeRefreshLayout?.isRefreshing = false
                Log.d("debug", "onError: " + error.message)
            }
        })
    }
}