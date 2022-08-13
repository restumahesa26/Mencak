package com.example.mencak.ui.detailpost

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.mencak.R
import com.example.mencak.adapter.CommentPostAdapter
import com.example.mencak.adapter.RelatedFoodAdapter
import com.example.mencak.databinding.ActivityDetailPostBinding
import com.example.mencak.model.CommentModel
import com.example.mencak.model.FoodModel
import com.example.mencak.model.PostModel

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding

    private var listComment = ArrayList<CommentModel>()
    private lateinit var rcComment: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.hide()
        setupView()

        val data = intent.getParcelableExtra<PostModel>("DATA")

        binding.tvNamePost.text = data?.name
        binding.tvTagPost.text = "#${data?.tag}"
        binding.tvTitlePost.text = data?.title
        binding.tvComment.text = "${data?.comment} comments"
        Glide.with(applicationContext)
            .load(data?.image)
            .transforms(CenterCrop(), RoundedCorners(16))
            .into(binding.ivPostImage)
        Glide.with(applicationContext)
            .load(data?.profil)
            .circleCrop()
            .into(binding.ivPostUser)

        rcComment = binding.rcListPost
        rcComment.setHasFixedSize(true)

        initDataDummy()

        var adapter = CommentPostAdapter(listComment)
        rcComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcComment.adapter = adapter
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    fun initDataDummy() {
        listComment = ArrayList()
        listComment.add(CommentModel("Mufti Restu Mahesa", "Ini Makanannya enak Banget, Aku suka dan Aku mau makan lagi", "https://res.cloudinary.com/fleetnation/image/private/c_fit,w_1120/g_south,l_text:style_gothic2:%C2%A9%20Karla,o_20,y_10/g_center,l_watermark4,o_25,y_50/v1582138872/k3vvyfxkrbpfvtxzm4p7.jpg"))
        listComment.add(CommentModel("Adde Nanda C. Putra", "Makanan ini enak banget, jadi ketagihan..pengen terusss", "https://i.pinimg.com/236x/84/cc/01/84cc013b01967f851d792083167fd82a.jpg"))
        listComment.add(CommentModel("M. Daffa Alfajri", "Ini guys, kalo kalian mau makan yang bikin lidah puas, cobain deh makanan ", "https://images.bauerhosting.com/legacy/lifestyle-images/celebrity/59ce55bd210e184115587fe9/Michelle_Keegan_fake_no_make_up_selfie.png"))
        listComment.add(CommentModel("Andrei Jonior Gustari", "Makanannya mengingatkan ku pada kenangan indah, lezat woi", "data:image/jpeg;base64,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"))
        listComment.add(CommentModel("Khoirus Fauzi", "Makanannya pas dilidah dan pas di kantong.. cobain deh", "https://thumbs.dreamstime.com/b/selfie-view-woman-taking-self-portrait-fake-her-mobile-kissing-expression-50273704.jpg"))
        listComment.add(CommentModel("Laila Nursita", "Yang manis, biasanya dicariin, nih ada makanan manis kaya kamu..", "https://www.cultofmac.com/wp-content/uploads/2019/06/selfie_dummy001.jpg"))
        listComment.add(CommentModel("Rizky Imanuel", "Mau manjain lidah, nih cobain makanan manis nya.. kuy", "https://i.pinimg.com/736x/ca/cf/fb/cacffb7126e76bb6a1bbcdd9acb1c34d.jpg"))
        listComment.add(CommentModel("Joko Satrio", "Kenapa rasa makanan ini tidak sebaik penampilannya..", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUxY7gfuGPATVzCGgV6vu3k_-O1hsignNLXw"))
        listComment.add(CommentModel("Brinda Ingke", "Makanannya tidak lebih enak dari buatan anak di umur 5 tahun, terimakasih", "https://media.tourbar.com/photos/1/3/6/513136/80080/8022_big.jpg?updated=1444732844"))
    }
}