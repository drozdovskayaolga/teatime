package com.example.teaceremony

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.drozdovskaya.teaceremony.R
import kotlinx.android.synthetic.main.activity_start_cooking.*
import kotlinx.android.synthetic.main.activity_tea_info.*
import kotlinx.android.synthetic.main.toolbar.*
import org.threeten.bp.Duration

class StartCooking : AppCompatActivity() {

    lateinit var  teaData: TeaData
    lateinit var  countDownTimer: CountDownTimer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_cooking)

        teaData = intent.getParcelableExtra("cook")
        setCookingData(teaData)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        countDownTimer = object : CountDownTimer(teaData.time, 1000){
            override fun onTick(p0: Long) {
                tv_Time.text = formatDuration(p0)
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "Time's OFF", Toast.LENGTH_LONG).show()
                tv_Time.text = "Готово!"
            }

        }

    }

    fun setCookingData(teaData: TeaData) {
        tv_Instruction.text = teaData.cook
        tv_Time.text = formatDuration(teaData.time)

    }

    fun start (view: View){
       countDownTimer.start()
//       tv_Left.isVisible = true
    }

    fun reset (view: View){
        countDownTimer.cancel()
        setCookingData(teaData)
//        tv_Left.isVisible = false
    }
}
