package com.sane4ek.aniroll.splash.data.model

import java.io.Serializable

class AnimeResultModel (
    val popular: ArrayList<AnimeEntity>,
    val ranked: ArrayList<AnimeEntity>,
    val newest: ArrayList<AnimeEntity>,
) : Serializable