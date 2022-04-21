package com.seniordesign.gunshotdetectionsystem

class DetectionEventHandler {
    var detectionListeners: MutableCollection<DetectionEventListener> = ArrayList()
    var listening: Boolean = true
    var detectionOccured: Boolean = false
    fun listenForEvents(){
        while(listening){
            //TODO LISTEN FOR EVENTS IN DATABASE
            if(detectionOccured){
                //TODO ACTUALLY GET DETECTION
                /*var detection: Detection = Detection(0.0,0.0, 0,
                "Test", "Test", ArrayList(), Detection.Status.INACTIVE)*/
               /* detectionListeners.forEach {
                    it.onDetection(detection)*/
                //}
            }
        }
    }

    fun addDetectionListener(detectionEventListener: DetectionEventListener){
/*
        detectionListeners.add(detectionEventListener)
*/
    }
}