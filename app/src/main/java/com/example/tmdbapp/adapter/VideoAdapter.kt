package com.example.tmdbapp.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore.Video
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.databinding.ReviewsLayoutBinding
import com.example.tmdbapp.databinding.VideoLayoutBinding
import com.example.tmdbapp.model.MovieReview
import com.example.tmdbapp.model.VideoKey
import com.example.tmdbapp.utils.constants

class VideoAdapter (val listItems : MutableList<VideoKey>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    val adapterData = listItems

    public class VideoViewHolder(private var binding: VideoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindings(VideoKey : VideoKey) {
            var completeURL = constants.YOUTUBE_BASE_URL + VideoKey.key // calculates the complete URL to be loaded into the webview
            Log.d("hello", "completeurl value is $completeURL")
            binding.video.settings.mediaPlaybackRequiresUserGesture = false
            binding.video.settings.domStorageEnabled = true
            binding.video.webViewClient = customClient(binding.root.context)
            binding.video.settings.javaScriptEnabled = true
            binding.video.webChromeClient = WebChromeClient()
            binding.video.loadUrl(completeURL)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VideoLayoutBinding.inflate(layoutInflater, parent, false)
        return VideoAdapter.VideoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
        holder.bindings(adapterData.get(position))
    }


    class customClient(inter : Context) : WebViewClient() {
        val context = inter
        override fun shouldOverrideUrlLoading(view : WebView?, url : String?) : Boolean {
            if (url?.startsWith("https://www.youtube.com/watch") == true) {
                try {
                    // Try to launch the YouTube app
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    intent.setPackage("com.google.android.youtube")
                    startActivity(context,intent,null)
                } catch (ex: ActivityNotFoundException) {
                    // If the YouTube app is not installed, launch the YouTube website in a browser
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(context,intent,null)
                }
                return true // Return true to indicate that the URL was handled
            }
            return super.shouldOverrideUrlLoading(view, url)
        }
        }

    }


