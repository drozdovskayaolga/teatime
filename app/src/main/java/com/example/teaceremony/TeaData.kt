package com.example.teaceremony

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeaData (
    val image: String,
    val name: String,
    val description: String,
    val time: Long,
    val cook: String,
    val infoImage: String) : Parcelable

