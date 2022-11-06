package com.example.sallachallenge.models.items

import com.google.gson.annotations.SerializedName


data class Promotion (

	val title : String?,
	@SerializedName("sub_title")
	val subTitle : String
)