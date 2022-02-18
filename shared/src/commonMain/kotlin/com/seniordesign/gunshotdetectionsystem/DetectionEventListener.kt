package com.seniordesign.gunshotdetectionsystem

interface DetectionEventListener {
    fun onDetection(detection: Detection)
}