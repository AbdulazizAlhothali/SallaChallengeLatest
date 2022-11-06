package com.example.sallachallenge.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.sallachallenge.models.developersjson.DevelopersJson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomTextView : AppCompatTextView {


    constructor(context: Context) : super(context){
        setFont(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        setFont(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setFont(context)
    }

    @Inject
    lateinit var developersJson: DevelopersJson


    private fun setFont(context: Context?){
        val myTypeface: Typeface = Typeface.createFromAsset(context?.assets,"myfonts/${developersJson.font_family}.ttf")
        typeface = myTypeface
    }

}