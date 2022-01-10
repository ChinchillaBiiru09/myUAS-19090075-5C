package com.example.uas19090075_mobileapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uas19090075_mobileapp.API.RetrofitClient
import com.example.uas19090075_mobileapp.Model.CreatePostResponse
import com.example.uas19090075_mobileapp.Model.PostResponse
import com.example.uas19090075_mobileapp.R
import kotlinx.android.synthetic.main.fragment_list_data.tv_ResponseCode
import kotlinx.android.synthetic.main.fragment_manipulation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ManipulationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manipulation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        updatePosts() // perbedaan put dan patch, put merubah semuanya sesuai dengan inputkan(input 'null' akan berubah ke null), sdgkan pacth dapat menyesuaikan(input 'null' maka akan menggunakan value sebelumnya)

        btn_update.setOnClickListener {
            updatePosts()
        }
    }

    private fun createPosts() {
        RetrofitClient.instance.createPost(
            18,
            "Fidah Retrofit",
            "Project UAS Individu Mobile Programming - 5C"
        ).enqueue(object : Callback<CreatePostResponse>{
            override fun onResponse(
                call: Call<CreatePostResponse>,
                response: Response<CreatePostResponse>
            ) {
                //jika berhasil
                val responeText = "Response Code : ${response.code()}\n" +
                        "Title : ${response.body()?.title}\n" + //body() -> untuk mengecek null atau tidak
                        "Body : ${response.body()?.text}\n" +
                        "userId : ${response.body()?.userId}\n" +
                        "id : ${response.body()?.id}"
                tv_ResponseCode.text = responeText
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                //set ketika ada error
                tv_ResponseCode.text = t.message
            }

        })
    }

    private fun deletePost() {
        RetrofitClient.instance.deletePosts(1).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                tv_ResponseCode.text = response.code().toString()
                //penasaran, mari kita manipulasi sendiri, gimana kalo misal tak ganti gini
//                if (response.code() == 200){
//                    tv_ResponseCode.text = "Berhasil Delete"
//                }
                //atau gini aja langsung
                tv_ResponseCode.text = "Berhasil"
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

        })
    }

    private fun updatePosts() {
        RetrofitClient.instance.patchPosts(
            et_id.text,
            et_userId.text,
            et_idFiekd.text,
            et_title.text.toString(),  // title -> null karna yang ingin dirubah hanya text/body nya
            et_desc.text.toString()
        ).enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                tv_ResponseCode.text = response.code().toString()
                val responeText = "Response Code : ${response.code()}\n" +
                        "Title : ${response.body()?.title}\n" + //body() -> untuk mengecek null atau tidak
                        "Body : ${response.body()?.text}\n" +
                        "userId : ${response.body()?.userId}\n" +
                        "id : ${response.body()?.id}"
                tv_ResponseCode.text = responeText
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

        })
    }
}