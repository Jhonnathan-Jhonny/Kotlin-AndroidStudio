@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.project.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AmphibiansModel (
    val name: String = "",
    val type: String = "",
    val description: String = "",
    @SerialName(value = "img_src")
    val imgSrc: String = ""
)