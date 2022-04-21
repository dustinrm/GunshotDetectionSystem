package com.seniordesign.gunshotdetectionsystem

class Record(
    val detection: Detection,
    val officer: Officer,
    val timeOfConfirmation: String,
) {
    fun export(exportType: ExportType, beginDate: String, endDate: String){
        //TODO
    }

    enum class ExportType{
        CSV,
        PNG,
        EMAIL
    }
}