package com.example.tmdbapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import androidx.core.content.ContextCompat.startActivity
import java.net.URI

class URLhandler {
    fun openURL(context: Context, url : String) {
        if(!URLUtil.isValidUrl(url)) { // ends function call if url is not valid
            return
        }
        val uri : Uri = Uri.parse(url)
        val myIntent : Intent = Intent(Intent.ACTION_VIEW)
        myIntent.data = uri
        startActivity(context,myIntent,null)
    }
}