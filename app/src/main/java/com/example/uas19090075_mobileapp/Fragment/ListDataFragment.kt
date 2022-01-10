package com.example.uas19090075_mobileapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas19090075_mobileapp.API.RetrofitClient
import com.example.uas19090075_mobileapp.Adapter.CommentAdapter
import com.example.uas19090075_mobileapp.Adapter.PostAdapter
import com.example.uas19090075_mobileapp.Model.CommentResponse
import com.example.uas19090075_mobileapp.Model.PostResponse
import com.example.uas19090075_mobileapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListDataFragment : Fragment() {

    private val list = ArrayList<PostResponse>() //ini kosong
    private val listComment = ArrayList<CommentResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showComments()
    }

    private fun showComments() {
        rv_Post.setHasFixedSize(true)
        rv_Post.layoutManager = LinearLayoutManager(activity)

        RetrofitClient.instance.getComments("posts/1/comments").enqueue(object : Callback<ArrayList<CommentResponse>> {
            override fun onResponse(
                call: Call<ArrayList<CommentResponse>>,
                response: Response<ArrayList<CommentResponse>>
            ) {
                tv_ResponseCode.text = "Get Data Sukses"
                response.body()?.let { listComment.addAll(it) }

                val adapter = CommentAdapter(listComment)
                rv_Post.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

        })
    }

    // Show Used Posts
    private fun showPosts() {
        //inisialisasi rv
        rv_Post.setHasFixedSize(true)
        //atur linear layout manager
        rv_Post.layoutManager = LinearLayoutManager(activity)

        //inisialisasi parameter hashmap
        val parameters = HashMap<String, String>()
        parameters["userId"] = "4"
        parameters["id"] = "32"

        //panggil retrofit client
        //utk callback pilih yang retrofit
        RetrofitClient.instance.getPosts(parameters).enqueue(object:
            Callback<ArrayList<PostResponse>> { //jika tanpa menggunakan manipulasi, param getPost harap dihapus
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                //mengambil response code
                val responseCode = response.code().toString()
                //tampilkan di tv
                tv_ResponseCode.text = responseCode
                //tambahkan list dengan data dari retrofit
                response.body()?.let { list.addAll(it)}
                //inisialisasi adapter
                val adapter = PostAdapter(list)
                //set adapter rv dg adapter yg telah dibuat
                rv_Post.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }
}