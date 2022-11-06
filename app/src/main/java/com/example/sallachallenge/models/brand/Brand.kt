package com.example.sallachallenge.models.brand

import com.google.gson.annotations.SerializedName


data class Brand (

	val id : Int,
	val name : String,
	val description : String,
	val banner : String,
	val logo : String,
	@SerializedName("ar_char")
	val arChar : String,
	@SerializedName("en_char")
	val enChar : String
)