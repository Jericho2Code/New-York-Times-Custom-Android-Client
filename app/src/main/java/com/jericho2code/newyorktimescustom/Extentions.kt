package com.jericho2code.newyorktimescustom

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Михаил on 30.01.2018.
 */
val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
val dateWithTimeFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

fun String.toDate(): Date = dateFormatter.parse(this)
fun Date.toDateWithTimeString(): String  = dateWithTimeFormat.format(this)
fun Date.toStringWithTimeZone():String = dateFormatter.format(this)

fun Context.openIntentOrShowErrorMessage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    // see stackoverflow.com/a/22479212/5246998
    val activityInfo = intent.resolveActivityInfo(this.packageManager, intent.flags)
    if (activityInfo != null && activityInfo.exported) {
        this.startActivity(intent)
    }
}