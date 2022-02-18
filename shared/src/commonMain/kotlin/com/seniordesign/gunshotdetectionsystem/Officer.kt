package com.seniordesign.gunshotdetectionsystem

class Officer(val id: Int, val name: String) {
    val detections: MutableCollection<Detection>
    val confirmations: HashMap<Detection, Boolean>
    init {
        //TODO add previous detections from database
        detections = ArrayList()
        confirmations = HashMap()
    }

    fun confirmDetection(detection: Detection, confirmation: Boolean){
        confirmations.put(detection, confirmation)
        detection.status = Detection.Status.INACTIVE
    }

}