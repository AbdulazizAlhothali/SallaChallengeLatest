package com.example.sallachallenge.models

import com.google.gson.annotations.SerializedName

data class ExampleJson2KtKotlin (

    @SerializedName("status"  ) var status  : Int?     = null,
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("error"   ) var error   : Error?   = Error()

)
