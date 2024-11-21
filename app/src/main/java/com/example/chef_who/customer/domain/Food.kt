package com.example.chef_who.customer.domain

import android.os.Parcelable
import com.example.chef_who.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val name:String,
    val price:Int,
    val info:String,
    val returnPolicy:String,
    val image:Int,
    val listOFImages:List<Int>
): Parcelable

val listOfFood = listOf(
    Food(
        "Veggie tomato mix",
        40,
        "Delivered between monday aug and thursday 20 from 8pm to 91:32 pm",
        "All our foods are double checked before leaving our stores so by any case you found a broken food please contact our hotline immediately.",
        R.drawable.food,
        listOf(
            R.drawable.food,
            R.drawable.food,
            R.drawable.food
        )
    ),
    Food(
        "Egg and cucmber",
        80,
        "Delivered between monday aug and thursday 20 from 8pm to 91:32 pm",
        "All our foods are double checked before leaving our stores so by any case you found a broken food please contact our hotline immediately.",
        R.drawable.egg,
        listOf(
            R.drawable.egg,
            R.drawable.egg,
            R.drawable.egg
        )
    ),
    Food(
        "Moi-moi and ekpa",
        90,
        "Delivered between monday aug and thursday 20 from 8pm to 91:32 pm",
        "All our foods are double checked before leaving our stores so by any case you found a broken food please contact our hotline immediately.",
        R.drawable.idlee,
        listOf(
            R.drawable.idlee,
            R.drawable.idlee,
            R.drawable.idlee
        )
    ),
    Food(
        "Fried chicken",
        100,
        "Delivered between monday aug and thursday 20 from 8pm to 91:32 pm",
        "All our foods are double checked before leaving our stores so by any case you found a broken food please contact our hotline immediately.",
        R.drawable.rice,
        listOf(
            R.drawable.rice,
            R.drawable.rice,
            R.drawable.rice
        )
    )
)