package com.seniordesign.gunshotdetectionsystem.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.seniordesign.gunshotdetectionsystem.Greeting
import android.widget.TextView
import com.seniordesign.gunshotdetectionsystem.ConnectionDriver
import com.seniordesign.gunshotdetectionsystem.Detection
import com.seniordesign.gunshotdetectionsystem.DetectionEventListener

class MainActivity : AppCompatActivity(), DetectionEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_view)

        val runnable = Runnable {
            val connectionDriver = ConnectionDriver()
            val detections = connectionDriver.SQLQuery()
            val detectionsLayout: LinearLayout = findViewById(R.id.slidingDetectionList)
            if(!detections.isEmpty()){
                runOnUiThread{detectionsLayout.removeAllViews()}
                for (detection in detections){
                    val detectionView = layoutInflater.inflate(R.layout.detection_list_item, null)
                    detectionView.findViewById<TextView>(R.id.detection_gps).setText(detection.lat.toString() + " , " + detection.lon)
                    //detectionView.findViewById<TextView>(R.id.detection_title).setText(detection.id)
                    detectionView.findViewById<TextView>(R.id.detection_time).setText(detection.time.toString())
                    detectionView.findViewById<TextView>(R.id.detection_date).setText(detection.date.toString())
                    detectionView.findViewById<TextView>(R.id.detection_status).setText(detection.status.toString())
                    try{
                        runOnUiThread{ detectionsLayout.addView(detectionView)}
                    }catch (e: Exception){
                        Log.e("MAIN ACTIVITY", e.printStackTrace().toString())
                    }

                }
            }else{
                val textView = TextView(applicationContext)
                textView.setText("EMPTY")
                detectionsLayout.addView(textView)
            }

        }
        //Thread(runnable).start()
        val intent = Intent(applicationContext, Maps::class.java)
        this.startActivity(intent)
    }

    override fun onDetection(detection: Detection) {
        TODO("Not yet implemented")
    }

}
