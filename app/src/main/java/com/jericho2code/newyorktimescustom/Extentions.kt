package com.jericho2code.newyorktimescustom

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Михаил on 30.01.2018.
 */
val dateIso8601TimeZoneFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)

fun String.toDate(): ZonedDateTime = ZonedDateTime.parse(this, dateIso8601TimeZoneFormatter)
fun ZonedDateTime.toDateWithTimeString(): String  = dateFormat.format(this)
fun ZonedDateTime.toStringWithTimeZone(): String = dateIso8601TimeZoneFormatter.format(this)

fun Context.openIntentOrShowErrorMessage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    // see stackoverflow.com/a/22479212/5246998
    val activityInfo = intent.resolveActivityInfo(this.packageManager, intent.flags)
    if (activityInfo != null && activityInfo.exported) {
        this.startActivity(intent)
    }
}
fun Context.shareMessage(message: String) {
    val sendIntent = Intent()
    sendIntent.action = Intent.ACTION_SEND
    sendIntent.putExtra(Intent.EXTRA_TEXT, message)
    sendIntent.type = "text/plain"
    // see stackoverflow.com/a/22479212/5246998
    val activityInfo = sendIntent.resolveActivityInfo(this.packageManager, sendIntent.flags)
    if (activityInfo != null && activityInfo.exported) {
        this.startActivity(sendIntent)
    }
}
