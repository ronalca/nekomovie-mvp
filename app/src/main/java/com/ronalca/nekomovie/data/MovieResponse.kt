/*
 * Copyright 2022 Rony Alcala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ronalca.nekomovie.data

import com.google.gson.annotations.SerializedName

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