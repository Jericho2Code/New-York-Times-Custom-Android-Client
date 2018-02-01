package com.jericho2code.newyorktimescustom.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Михаил on 29.01.2018.
 */
@Entity(tableName = "articles")
class Article {
    var url: String? = null
    //it is bad idea to make url primary key, but i want make project simple as posible, so whatever ;)
    @field:[PrimaryKey SerializedName("short_url")] var shortUrl: String = ""
    var section: String? = null
    var subsection: String? = null
    var byline: String? = null
    @field:SerializedName("item_type") var itemType: String? = null
    var abstract: String? = null
    var source: String? = null
    @field:SerializedName("updated_date") var updatedDate: Date? = null
    @field:SerializedName("created_date") var createdDate: Date? = null
    @field:SerializedName("published_date") var publicationDate: Date? = null
    @field:SerializedName("des_facet") var desFacet: List<String>? = null
    @field:SerializedName("org_facet") var orgFacet: List<String>? = null
    @field:SerializedName("per_facet") var perFacet: List<String>? = null
    @field:SerializedName("geo_facet") var geoFacet: List<String>? = null
    var title: String? = null
    var multimedia: List<Multimedia>? = null
}