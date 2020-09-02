package com.example.teaceremony

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tea_info.*
import kotlinx.android.synthetic.main.toolbar.*
import android.widget.Toast
import com.drozdovskaya.teaceremony.R
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.CropTransformation
import com.makeramen.roundedimageview.RoundedTransformationBuilder

class TeaInfoActivity : AppCompatActivity() {

    lateinit var  teaData: TeaData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tea_info)

        teaData = intent.getParcelableExtra("details")
        setData(teaData)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    fun setData(data: TeaData) {

        tv_Tea_info.text = data.name
        tv_Description.text = data.description

//        val transformation = RoundedTransformationBuilder()
//           .borderColor(Color.BLACK)
//            .borderWidthDp(3f)
//            .cornerRadiusDp(30f)
//            .oval(true)
//            .build()

        Picasso.with(this)
            .load(data.infoImage)
            .resize(450, 450)
            .centerCrop()
            .transform(CropCircleTransformation())
            .into(iv_Tea_info)
    }

    fun start(view: View) {
        val i = Intent(applicationContext, StartCooking::class.java)
        i.putExtra("cook", teaData)
        startActivity(i)
    }


}
