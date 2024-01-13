package com.rosan.installer.util

import com.rosan.installer.data.app.model.entity.DataEntity
import com.rosan.installer.data.app.util.sourcePath
import java.io.File
import java.math.RoundingMode
import java.text.DecimalFormat

private const val MB = 1024 * 1024
fun getApkSize(data: DataEntity): Float {
    val file = File(data.sourcePath())
    val length = file.length().toFloat()
    val format = DecimalFormat("#.##")
    format.roundingMode = RoundingMode.FLOOR
    return format.format(length / MB).toFloat()
}