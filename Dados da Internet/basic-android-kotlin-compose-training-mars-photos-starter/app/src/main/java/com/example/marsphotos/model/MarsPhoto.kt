package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MarsPhoto (
    val id: String,
    // Para usar nomes de variáveis na sua classe de dados que sejam
    // diferentes dos nomes de chave da resposta JSON
    @SerialName(value = "img_src")
    val imgSrc: String
)