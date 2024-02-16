package com.amalitech.arms_mobile.services

import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

object MSGRequestWrapper {

    private const val TAG = "MSGraphRequestWrapper"
    private const val MS_GRAPH_ROOT_ENDPOINT = "https://graph.microsoft.com/"
    const val DEFAULT_GRAPH_RES_URL = MS_GRAPH_ROOT_ENDPOINT + "v1.0/me"
    const val GRAPH_PHOTO_RES_URL = "$DEFAULT_GRAPH_RES_URL/photo/\$value"

    fun callGraphAPI(
        context: Context,
        accessToken: String,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        // Make sure we have a token to send to graph
        if (accessToken.isEmpty()) {
            return
        }

        val queue: RequestQueue = Volley.newRequestQueue(context)
        val parameters = JSONObject().apply {
            try {
                put("key", "value")
            } catch (e: Exception) {
                Log.d(TAG, "Failed to put parameters: $e")
            }
        }

        val request = object : JsonObjectRequest(
            Method.GET, DEFAULT_GRAPH_RES_URL, parameters,
            responseListener, errorListener
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $accessToken"
                return headers
            }
        }

        Log.d(TAG, "Adding HTTP GET to Queue, Request: $request")
        Log.d(TAG, "Adding HTTP GET to Queue, Request: ${request.method}")

        request.retryPolicy = DefaultRetryPolicy(
            3000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(request)
    }

    fun callGraphPhotoAPI(
        context: Context,
        accessToken: String,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ){
        if (accessToken.isEmpty()) {
            return
        }

        val queue: RequestQueue = Volley.newRequestQueue(context)
        val parameters = JSONObject().apply {
            try {
                put("key", "value")
            } catch (e: Exception) {
                Log.d(TAG, "Failed to put parameters: $e")
            }
        }

        val request = object : JsonObjectRequest(
            Method.GET, GRAPH_PHOTO_RES_URL, parameters,
            responseListener, errorListener
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $accessToken"
                return headers
            }
        }

        Log.d(TAG, "Making HTTP Photo: $request")

        request.retryPolicy = DefaultRetryPolicy(
            3000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(request)
    }
}