package com.example.newsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source?,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
): Parcelable{
    // Override equals and hashCode to handle source being nullable
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Article) return false
        return title == other.title &&
                description == other.description &&
                source?.name == other.source?.name // Safe null comparison for source
    }

    override fun hashCode(): Int {
        return title.hashCode() * 31 +
                description.hashCode() * 31 +
                (source?.name?.hashCode() ?: 0) // Safe null handling for source
    }
}
