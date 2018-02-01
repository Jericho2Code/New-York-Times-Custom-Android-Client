package com.jericho2code.newyorktimescustom.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "bookmarks")
class Bookmark {
    @field:PrimaryKey(autoGenerate = true) var id: Long = 0
    var shortUrl: String? = null
    var title: String? = null
    var abstract: String? = null
    var publicationDate: Date? = null
    var imageUrl: String? = null}