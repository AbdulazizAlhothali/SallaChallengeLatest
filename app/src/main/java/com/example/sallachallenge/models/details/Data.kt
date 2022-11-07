package com.example.sallachallenge.models.details

import com.google.gson.annotations.SerializedName


data class Data (

    val id: String,
    val sku: String,
    val name: String,
    val description: String,
    val url: String,

    @SerializedName("promotion_title")
    val promotionTitle: String?,

    val subtitle: String,
    val type: String,
    val status: String,
    val price: Double,

    @SerializedName("sale_price")
    val salePrice: Double,

    @SerializedName("regular_price")
    val regularPrice: Double,

    @SerializedName("starting_price")
    val startingPrice: Double? = null,

    val quantity: Long,

    @SerializedName("max_quantity")
    val maxQuantity: Long,

    @SerializedName("discount_ends")
    val discountEnds: Long,

    @SerializedName("is_taxable")
    val isTaxable: Boolean,

    @SerializedName("has_read_more")
    val hasReadMore: Boolean,

    @SerializedName("can_add_note")
    val canAddNote: Boolean,

    @SerializedName("can_show_remained_quantity")
    val canShowRemainedQuantity: Boolean,

    @SerializedName("can_upload_file")
    val canUploadFile: Boolean,

    @SerializedName("has_custom_form")
    val hasCustomForm: Boolean,

    @SerializedName("is_on_sale")
    val isOnSale: Boolean,

    @SerializedName("is_hidden_quantity")
    val isHiddenQuantity: Boolean,

    @SerializedName("is_available")
    val isAvailable: Boolean,

    @SerializedName("is_out_of_stock")
    val isOutOfStock: Boolean,

    val weight: String,
    val calories: Any? = null,
    val image: Image
)