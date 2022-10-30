package com.example.sallachallenge.models.items




data class Data (

    val id : Int,
    val type : String,
    val promotion : Promotion,
    val status : String,
    val is_available : Boolean,
    val sku : String,
    val name : String,
    val price : Price,
    val sale_price : Sale_price,
    val currency : String,
    val url : String,
    val thumbnail : String,
    val has_special_price : Boolean,
    val regular_price : Regular_price,
    val calories : String,
    val mpn : String,
    val gtin : String,
    val favorite : String,
	//val starting_price : String
)