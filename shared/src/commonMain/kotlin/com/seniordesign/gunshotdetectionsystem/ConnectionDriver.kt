package com.seniordesign.gunshotdetectionsystem
import java.lang.Class
import java.sql.DriverManager
import java.lang.ClassNotFoundException
import android.util.Log
class ConnectionDriver {

    fun SQLQuery(): MutableCollection<Detection>{
        val TAG = "ConnectionDriver";
        //URL FOR Java Connector
        val ip = "192.168.231.80"
        //"192.168.0.115"
        val port = "1433"
        val className = "net.sourceforge.jtds.jdbc.Driver"
        val database = "GunshotDetection"
        val username = "test2"
        val password = "test2"
        val url = "jdbc:jtds:sqlserver://$ip:$port/$database"
        //val JDBCUrl = "jdbc:sqlserver://172.223.37.81:1433;databaseName=Detection"
        try {
            Class.forName(className)
            val connectionDriver = DriverManager.getConnection(url,username,password)
            //get the connection
            val query = connectionDriver.prepareStatement("SELECT * FROM DETECTION")

            val results = query.executeQuery()

            val detections = ArrayList<Detection>()

            while (results.next()) {
                val id = results.getInt("DetectionID")

                val lat = results.getDouble("Lat")
                val lon = results.getDouble("Lon")

                val time = results.getTime("DetectionTime")

                val date = results.getDate("DetectionDate")

                val officerId = results.getInt("OfficerId")

                val detection = Detection(lat, lon, id, date, time)
                detections.add(detection)
            }
            return detections
        }catch (e: ClassNotFoundException){
            Log.e(TAG,"Class not found exception")
        }catch (e: java.sql.SQLException){
            Log.e(TAG, "SQL EXCEPTION")
        }catch (e: Exception){
            Log.e(TAG, e.printStackTrace().toString())
        }



        return ArrayList<Detection>()

    }
}