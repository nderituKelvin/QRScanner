package com.codenderitu.qrcodereader

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var txtViewResult : TextView
    private lateinit var btnScan : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtViewResult = findViewById(R.id.result)
        btnScan = findViewById(R.id.btnScan)

        btnScan.setOnClickListener {
            val intent = Intent(applicationContext, ScanActivity::class.java)
            startActivityForResult(intent, 200)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 200){
            if(resultCode == Activity.RESULT_OK){
                txtViewResult.text = data?.getStringExtra("result")
            }
        }
    }
}
