package com.seniordesign.gunshotdetectionsystem
import java.sql.Date;
import java.sql.Time;
data class Detection(
    val lat: Double,
    val lon: Double,
    val id: Int,
    val date: Date,
    val time: Time,
    var status: Status = Status.ACTIVE
    ) {
    enum class Status{
        ACTIVE,
        INACTIVE
    }
}