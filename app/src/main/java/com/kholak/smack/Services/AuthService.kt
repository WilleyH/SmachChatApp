package com.kholak.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kholak.smack.Utilities.URL_REGISTER
import org.json.JSONObject

/**
 * Created by nsilska on 2017-12-08.
 */
object AuthService {

    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {
        val url = URL_REGISTER

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val registerRequest = object : StringRequest(Method.POST, URL_REGISTER, Response.Listener {
            complete(true)
        }, Response.ErrorListener {
            error -> Log.d("ERROR", "Could not register user $error")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; character=utf-8"
                }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
                }
            }

        Volley.newRequestQueue(context).add(registerRequest)

        }

    }
}