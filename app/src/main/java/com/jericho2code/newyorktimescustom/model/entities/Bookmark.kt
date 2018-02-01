package com.jericho2code.newyorktimescustom.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "bookmarks")
class Bookmark (
    @field:PrimaryKey(autoGenerate = true) var id: Long,
    var shortUrl: String,
    var title: String,
    var abstract: String,
    var publicationDate: Date,
    var imageUrl: String)