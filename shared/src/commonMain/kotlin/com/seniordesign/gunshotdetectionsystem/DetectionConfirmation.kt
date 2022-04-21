package com.seniordesign.gunshotdetectionsystem

data class DetectionConfirmation(val confirmation: Boolean,
                                 val detection: Detection,
                                 val time: String) {
}