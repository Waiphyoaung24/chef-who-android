package com.example.chef_who.customer.domain

import android.os.Parcelable
import com.example.chef_who.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val id : Int,
    val name:String,
    val price:Int,
    val info:String,
    val returnPolicy:String,
    val image:String,
    val listOFImages:List<Int>
): Parcelable

