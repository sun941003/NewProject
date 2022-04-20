package com.example.newproject.model.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class JsonData(
    val `1st_count`: Long=0,
    val `1st_money`: String="",
    val `2nd_count`: Long=0,
    val `2nd_money`: String="",
    val `3th_count`: Any? =null,
    val `3th_money`: String="",
    val `4th_count`: String="",
    val `4th_money`: String="",
    val `5th_count`: String="",
    val `5th_money`: String="",
    val date: String="",
    val no1: Long=0,
    val no2: Long=0,
    val no3: Long=0,
    val no4: Long=0,
    val no5: Long=0,
    val no6: Long=0,
    val no7: Long=0,
    val times: Long=0
//times도 1000넘어가면 스트링 값으로 바뀔꺼 같음
){

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "1st_count" to `1st_count`.toLong() ,
            "1st_money" to `1st_money`,
            "2nd_count" to `2nd_count`,
            "2nd_money" to `2nd_money`,
            "3th_count" to `3th_count`,
            "3th_money" to `3th_money`,
            "4th_count" to `4th_count`,
            "4th_money" to `4th_money`,
            "5th_count" to `5th_count`,
            "5th_money" to `5th_money`,
            "date" to date,
            "no1" to no1,
            "no2" to no2,
            "no3" to no3,
            "no4" to no4,
            "no5" to no5,
            "no6" to no6,
            "no7" to no7,
            "times" to times
        )
    }
}