package com.example.tmdbapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide



@BindingAdapter("posterImageUrl")

fun bindPosterImage(imgView: ImageView, imgUrl : String) {
    imgUrl.let {//this is the same as if imgurl != null
        Glide
            .with(imgView)
            .load(constants.POSTER_IMAGE_BASE_URL + constants.POSTER_IMAGE_WIDTH + it)
            .into(imgView)


    }
}
@BindingAdapter("backdropImageUrl")
fun bindBackdropImage(imgView: ImageView, imgUrl : String) {
    imgUrl.let {//this is the same as if imgurl != null
        Glide
            .with(imgView)
            .load(constants.BACKDROP_IMAGE_BASE_URL + constants.BACKDROP_IMAGE_WIDTH + it)
            .into(imgView)


    }
}