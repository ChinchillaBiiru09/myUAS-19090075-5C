package com.example.uas19090075_mobileapp.API


import android.text.Editable
import com.example.uas19090075_mobileapp.Model.CommentResponse
import com.example.uas19090075_mobileapp.Model.CreatePostResponse
import com.example.uas19090075_mobileapp.Model.PostResponse
import retrofit2.Call
import retrofit2.http.*

interface API {
//    @GET("posts")
//    fun getPost(): Call<ArrayList<PostResponse>> //Call sebagai nilai balik

    //manipulas url 2, dengan query
    //manipulasi query bisa menggunakan kebih dari 1 query, tergantung api yg dituju
    @GET("https://jsonplaceholder.typicode.com/posts") //-> semisal ingin menggunakan url yg berbeda, bsa mengguakan cara ini, yaitu dengan menimpa atau memasukan lgsg url nya ke dalam anotasi
    fun getPosts(
        @Query("userId") userId: Int,
        @Query("id") id: Int): Call<ArrayList<PostResponse>>

    //manipulasi url dengan query 2, lebih fleksibel dg hashmap apabila query yang digunakan sangat banyak
    @GET("posts")
    fun getPosts(
        @QueryMap parameters: HashMap<String, String> //<key, value>
    ): Call<ArrayList<PostResponse>>

    //manippulasi url 3
    @GET
    fun getComments(@Url url: String): Call<ArrayList<CommentResponse>>

    //manipulasi url dengan id
    @GET("posts/{id}/comments")
    fun  getComments(@Path("id") postId: Int): Call<ArrayList<CommentResponse>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String
    ): Call<CreatePostResponse>

    // UPDATE
    @FormUrlEncoded
    @PUT("posts/{id}")
    fun putPosts(
        @Path("id") id: Int, //-> untuk url
        @Field("userId") userId: Int,
        @Field("id") idField: Int, //-> untuk update
        @Field("title") title: String?,
        @Field("body") text: String?
    ): Call<PostResponse>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patchPosts(
        @Path("id") id: Editable, //-> untuk url
        @Field("userId") userId: Editable,
        @Field("id") idField: Editable, //-> untuk update
        @Field("title") title: String?,
        @Field("body") text: String?
    ): Call<PostResponse>

    //DELETE
    @DELETE("posts/{no}") //value didalam {} harus sama dengan bawahnya
    fun deletePosts(@Path("no") id: Int): Call<Void>
}