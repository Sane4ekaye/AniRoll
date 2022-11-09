package com.sane4ek.aniroll.splash.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimeModel(
    @SerializedName("data")
    val `data`: Data
) : Serializable

data class Data(
    @SerializedName("Page")
    val Page: Page
) : Serializable

data class Page(
    @SerializedName("media")
    val media: ArrayList<AnimeEntity>
) : Serializable

data class AnimeEntity(
    @SerializedName("coverImage")
    val coverImage: CoverImage,
    @SerializedName("description")
    val description: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("meanScore")
    val meanScore: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("source")
    val source: String,
    @SerializedName("title")
    val title: Title,
    @SerializedName("volumes")
    val volumes: Any
) : Serializable

data class Title(
    @SerializedName("english")
    val english: String,
    @SerializedName("native")
    val native: String
) : Serializable

data class CoverImage(
    @SerializedName("large")
    val large: String,
) : Serializable