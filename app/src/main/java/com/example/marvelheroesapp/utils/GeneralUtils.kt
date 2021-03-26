package com.example.marvelheroesapp.utils

import com.example.marvelheroesapp.data.remote.api.ApiEndpoint.PRIVATE_API_KEY
import com.example.marvelheroesapp.data.remote.api.ApiEndpoint.PUBLIC_API_KEY
import java.math.BigInteger
import java.security.MessageDigest

class GeneralUtils {

    companion object {
        private const val SECONDS = 1000
        const val UNKNOWN_ERROR = "Unknown Error"
        private const val SLASH = "/"
        private const val DOT = "."
        private const val API_IMAGE_SIZE = "landscape_xlarge"

        fun getCurrentTimeStampInSeconds(): String {
            return (System.currentTimeMillis() / SECONDS).toString()
        }

        fun getMD5Hash(timeStamp: String): String {
            val inputString: String = timeStamp + PRIVATE_API_KEY + PUBLIC_API_KEY
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(inputString.toByteArray()))
                .toString(16).padStart(32, '0')
        }

        fun getComposedImageUrl(originalUrl : String, imageExtension : String) : String{
            return originalUrl + SLASH + API_IMAGE_SIZE + DOT +imageExtension
        }
    }
}