package com.example.FireFlix.retrofit.email_check

import com.example.FireFlix.tmdbapidataclass.MailerooResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailCheckApiServices {
    data class EmailRequest(val api_key: String, val email_address: String)

    @POST("check")
    suspend fun checkEmailAddress(
        @Body request: EmailRequest
    ): MailerooResponse
}
