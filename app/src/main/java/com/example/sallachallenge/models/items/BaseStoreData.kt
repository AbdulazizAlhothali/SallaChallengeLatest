package com.example.sallachallenge.models.items




data class BaseStoreData (

    val status : Int,
    val success : Boolean,
    val data : List<Data>,
    val cursor : Cursor
)