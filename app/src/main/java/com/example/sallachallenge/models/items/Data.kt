package com.example.sallachallenge.models.items

import com.google.gson.annotations.SerializedName


data class Data (

    val id : Int,
    val type : String,
    val promotion : Promotion,
    val status : String,
    @SerializedName("is_available")
    val isAvailable : Boolean,
    val sku : String,
    val name : String,
    val price : Price,
    @SerializedName("sale_price")
    val salePrice : SalePrice,
    val currency : String,
    val url : String,
    val thumbnail : String,
    val has_special_price : Boolean,
    val regular_price : RegularPrice,
    val calories : String,
    val mpn : String,
    val gtin : String,
    val favorite : String,
)