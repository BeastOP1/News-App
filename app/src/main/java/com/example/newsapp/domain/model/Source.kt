package com.example.newsapp.domain.model

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val id: String,
    val name: String
): Parcelable{
    // Override equals and hashCode to handle source name being nullable
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Source) return false
        return name == other.name
                && id ==other.id
    }

    override fun hashCode(): Int {
        var result = name.hashCode() // Use name directly, as it's non-nullable
        result = 31 * result + id.hashCode() // Combine id's hashCode with name's hashCode
        return result
    }
}