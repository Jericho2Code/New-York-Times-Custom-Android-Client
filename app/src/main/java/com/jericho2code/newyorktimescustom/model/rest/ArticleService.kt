package com.jericho2code.newyorktimescustom.model.rest

import com.jericho2code.newyorktimescustom.model.response.ArchiveResponse
import com.jericho2code.newyorktimescustom.model.rest.RestConstants.API_ARCHIVE_V1
import com.jericho2code.newyorktimescustom.model.rest.RestConstants.API_TOPSTORIES_V2
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ArticleService {
    @GET(API_ARCHIVE_V1 + "/{year}/{month}.json")
    fun getArticleForMonth(@Path("month") month: Int, @Path("year") year: Int, @QueryMap options: Map<String, String>): Single<ArchiveResponse>

    @GET(API_TOPSTORIES_V2 + "/{category}.json")
    fun getArticleByCategory(@Path("category") category: String, @QueryMap options: Map<String, String>): Single<ArchiveResponse>

}