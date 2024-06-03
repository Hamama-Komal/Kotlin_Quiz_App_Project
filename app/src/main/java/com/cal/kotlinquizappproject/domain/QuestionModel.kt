package com.cal.kotlinquizappproject.domain

import android.os.Parcel
import android.os.Parcelable

data class QuestionModel(
    val id:Int?,
    val question:String?,
    val option1:String?,
    val option2:String?,
    val option3:String?,
    val option4:String?,
    val correctAnswer:String?,
    val score:Int?,
    val picPath: String?,
    var clickedOption:String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(question)
        parcel.writeString(option1)
        parcel.writeString(option2)
        parcel.writeString(option3)
        parcel.writeString(option4)
        parcel.writeString(correctAnswer)
        parcel.writeValue(score)
        parcel.writeString(picPath)
        parcel.writeString(clickedOption)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionModel> {
        override fun createFromParcel(parcel: Parcel): QuestionModel {
            return QuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<QuestionModel?> {
            return arrayOfNulls(size)
        }
    }
}
