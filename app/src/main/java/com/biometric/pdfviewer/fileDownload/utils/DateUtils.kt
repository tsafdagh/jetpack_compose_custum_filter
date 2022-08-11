package com.biometric.pdfviewer.fileDownload.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

object MyDateUtils {


    fun Date.timeAgo(): String {
        val diff: Long = Date().time - this.time

        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val month = days / 30
        val years = month / 12

        val words: String

        words = when {
            seconds < 60 -> {
                "${seconds.toInt()} Seconds ago"
            }
            minutes < 60 -> {
                "${minutes.toInt()} Minutes ago"
            }
            hours < 24 -> {
                "${hours.toInt()} Hours ago"
            }
            days < 30 -> {
                "${days.toInt()} Days ago"
            }
            month < 12 -> {
                "${month.toInt()} Month ago"
            }
            else -> {
                "${years.toInt()} Years ago"
            }
        }

        return words
    }


    fun difBetweenTwoDate(startDate: Date, endDate: Date) {
        val diffInMillisec: Long = startDate.time - endDate.time

        val diffInSec: Long = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec)
        println("diffInSec: $diffInSec")

        val diffInMin: Long = TimeUnit.MILLISECONDS.toMinutes(diffInMillisec)
        println("diffInMin: $diffInMin")

        val diffInHours: Long = TimeUnit.MILLISECONDS.toHours(diffInMillisec)
        println("diffInHours: $diffInHours")

        val diffInDays: Long = TimeUnit.MILLISECONDS.toDays(diffInMillisec)
        println("diffInDays: $diffInDays")

    }

    fun Date.addDaytoDate(number: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = this
        cal.add(Calendar.DAY_OF_MONTH, number)
        return cal.time
    }

    fun Date.addHoursToDate(number: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = this
        cal.add(Calendar.HOUR, number)
        return cal.time
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun convertStringToLocalDateTime(stringDate: String): LocalDateTime {

        val dateTime = LocalDateTime.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))

        return dateTime

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fromLdt(ldt: LocalDateTime): Date {
        val zdt: ZonedDateTime = ZonedDateTime.of(ldt, ZoneId.of("UTC"))
        val cal = GregorianCalendar.from(zdt)
        return cal.time
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateNowInUTC():Date{
        val now = ZonedDateTime.now(ZoneId.of("UTC"))
        val cal = GregorianCalendar.from(now)
        return cal.time
    }
}