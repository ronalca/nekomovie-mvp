package com.ronalca.nekomovie.model

import com.google.gson.annotations.SerializedName

/**
 * This data class defines movie categories which includes name and videos.
 * The property names of this data class are used by Gson to match the names of values in JSON.
 */

data class MovieResponse (
    @SerializedName("categories" ) var categories : ArrayList<Categories> = arrayListOf()
)

data class Categories (
    @SerializedName("name"   ) var name   : String?           = null,
    @SerializedName("videos" ) var videos : ArrayList<Videos> = arrayListOf()
)

data class Videos (
    @SerializedName("subtitle"       ) var subtitle       : String?           = null,
    @SerializedName("sources"        ) var sources        : ArrayList<String> = arrayListOf(),
    @SerializedName("thumb"          ) var thumb          : String?           = null,
    @SerializedName("image-480x270"  ) var image480x270   : String?           = null,
    @SerializedName("image-780x1200" ) var image780x1200  : String?           = null,
    @SerializedName("title"          ) var title          : String?           = null,
    @SerializedName("studio"         ) var studio         : String?           = null
)