package com.jericho2code.newyorktimescustom.model.response

import com.google.gson.annotations.SerializedName
import com.jericho2code.newyorktimescustom.model.entities.Article
import java.util.*

/**
 * Created by Михаил on 29.01.2018.
 */
class ArchiveResponse {
    var status: String? = null
    var copyright: String? = null
    var section: String? = null
    @field:SerializedName("last_updated") var lastUpdate: Date? = null
    var results: List<Article>? = null
}