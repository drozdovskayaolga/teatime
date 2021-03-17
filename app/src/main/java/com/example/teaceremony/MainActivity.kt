package com.example.teaceremony

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.drozdovskaya.teaceremony.R
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}


//        toolbar.navigationIcon = null
//
//        val adapter = TeaListAdapter(generateTeaData())
//
//        adapter.onItemClickListener = {
//
//            val i = Intent(applicationContext, TeaInfoActivity::class.java)
//            i.putExtra("details", it)
//            startActivity(i)
//        }
//
//        rvTea.adapter = adapter
//        rvTea.layoutManager = GridLayoutManager(this, 2)





