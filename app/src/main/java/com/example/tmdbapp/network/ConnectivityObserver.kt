package com.example.tmdbapp.network

import kotlinx.coroutines.flow.Flow
import javax.net.ssl.SSLEngineResult.Status

interface ConnectivityObserver {

    fun observe() : Flow<Status>

    enum class Status {
        Available,Unavailable, Losing, Lost
    }

}