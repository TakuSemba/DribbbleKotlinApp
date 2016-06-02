package com.takusemba.dribbblesampleapp.network


import com.takusemba.dribbblesampleapp.R
import com.takusemba.dribbblesampleapp.model.Shot

import retrofit.Callback
import retrofit.http.GET
import retrofit.http.Query

/**
 * Created by takusemba on 15/11/09.
 */
interface ShotApi {

    @GET("/shots/?access_token=$ACCESS_TOKEN&per_page=24")
    fun fetchShots(@Query("list") type: String, @Query("page") page: Int, callback: Callback<List<Shot>>)

    enum class Type internal constructor(key: String) {
        ANIMATED("animated"),
        ATTACHMENTS("attachments"),
        DEBUTS("debuts"),
        PLAYOFFS("playoffs"),
        REBOUNDS("rebounds"),
        TEAMS("teams");

        var key: String
            internal set

        init {
            this.key = key
        }
    }

    companion object {

        const val ACCESS_TOKEN = "67f7f46ae24ee28dac71ee61f97f2fb665f31fca2f9c4ff0dac49836011b3a32"
    }

}
