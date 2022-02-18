package com.seniordesign.gunshotdetectionsystem.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seniordesign.gunshotdetectionsystem.Greeting
import android.widget.TextView
import com.seniordesign.gunshotdetectionsystem.Detection
import com.seniordesign.gunshotdetectionsystem.DetectionEventListener

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity(), DetectionEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO IMPORT PREVIOUS OFFICERS AND DETECTIONS FROM DATABASE
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }

    override fun onDetection(detection: Detection) {
        TODO("Not yet implemented")
    }
}
