package com.myexperiment1.data.network

import com.myexperiment1.model.resposnses.PostListResposnse
import com.myexperiment1.model.resposnses.Post
import com.myexperiment1.util.ApiConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlaceHolderApiService {

    @GET(ApiConstants.PHOLDER_POSTSALL)
    suspend fun getAllPost(): Response<PostListResposnse>

    @GET("${ApiConstants.PHOLDER_POSTSALL}/{postId}")
    suspend fun getOnlyPost(@Path(value = "postId") postId: String): Response<Post>

    @POST("${ApiConstants.PHOLDER_POST_CREATE}")
    suspend fun createPost(@Body post: Post): Response<Post>

}