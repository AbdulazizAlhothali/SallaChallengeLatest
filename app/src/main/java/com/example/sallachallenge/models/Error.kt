package com.example.sallachallenge.models

import com.google.gson.annotations.SerializedName

data class Error (

    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("message" ) var message : String? = null

)
