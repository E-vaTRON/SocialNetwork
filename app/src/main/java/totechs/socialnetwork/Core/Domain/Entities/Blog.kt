package totechs.socialnetwork.Core

import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.LocalDateTime
import java.util.UUID

data class Blog(
    override var Id: String,
    override var Deleted: Boolean,
    override var CreatedOn: LocalDateTime?,
    override var LastUpdatedOn: LocalDateTime?,
    val Title: String,
    val Description: String,
    val ImageUrl: String,
    val Tags: List<Tag>,
) : EntityBase()