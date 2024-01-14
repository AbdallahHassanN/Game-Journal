package com.example.steamdbmockup.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.example.steamdbmockup.BaseApplication
import com.example.steamdbmockup.network.response.GameResponse
import com.example.steamdbmockup.network.response.Resource
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

fun <T> handleResponse(response: Response<T>, context: Context): Flow<Resource<T>> {


    return flow {
        if (isNetworkConnected(context)) {
            emit(Resource.Loading())

            if (response.isSuccessful) {
                when (response.code()) {
                    in 200..300 -> {
                        if (response.body() == null) {
                            emit(Resource.Error("Empty Data"))
                            // Log.d(Constants.TAG, "handle Empty Data")
                        } else {
                            emit(Resource.Success(response.body()!!))
                            // Log.d(Constants.TAG, "handle ${response.body()}")
                        }
                    }

                    in 301..400 -> {
                        // Log.e("MyRepositoryImpl", "Network Error : ${response.message()}")
                        emit(Resource.Error(message = response.message()))
                        // Log.d(Constants.TAG, "handle Network Error")
                    }

                    else -> {
                        // Log.e("MyRepositoryImpl", "Server Error : ${response.message()}")
                        emit(Resource.Error(message = response.message()))
                        // Log.d(Constants.TAG, "handle Server Error")
                    }
                }
            } else {
                // Log.e("MyRepositoryImpl", "Something went wrong with : ${response.message()}")
                emit(Resource.Error(message = response.message()))
            }
        } else {
            // No network connectivity
            emit(Resource.Error("No network connection"))
        }
    }
}


private fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}