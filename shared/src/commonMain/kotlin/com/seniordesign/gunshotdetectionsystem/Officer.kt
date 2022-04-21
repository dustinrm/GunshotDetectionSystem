package com.seniordesign.gunshotdetectionsystem

class Officer(val id: Int, val name: String) {
    val confirmations: MutableCollection<DetectionConfirmation>
    init {
        //TODO add previous detections from database
        confirmations = ArrayList()
    }

    fun confirmDetection(detection: Detection, confirmation: Boolean){
        val detectionConfirmation = DetectionConfirmation(confirmation, detection, "TIME")
        confirmations.add(detectionConfirmation)
        detection.status = Detection.Status.INACTIVE
    }

}