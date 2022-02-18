package com.seniordesign.gunshotdetectionsystem

data class Detection(
    val lat: Double,
    val lon: Double,
    val id: Int,
    val date: String,
    val time: String,
    val nodeIds: List<String>,
    var status: Status
    ) {
    enum class Status{
        ACTIVE,
        INACTIVE
    }
}