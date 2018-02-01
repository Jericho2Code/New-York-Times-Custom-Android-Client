package com.jericho2code.newyorktimescustom

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by Михаил on 15.11.2017.
 */
@GlideModule
class MyGlideAppModule : AppGlideModule() {
    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        builder?.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
    }
}