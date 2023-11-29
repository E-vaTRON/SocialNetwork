package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import totechs.socialnetwork.Core.Blog
import totechs.socialnetwork.Infrastructure.EntityBase
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class Blog(
    @PrimaryKey
    override var Id: UUID,
    override var Deleted: Boolean,
    override var CreatedOn: LocalDateTime?,
    override var LastUpdatedOn: LocalDateTime?,
    val Title: String,
    val Description: String,
    val ImageUrl: String,
) : EntityBase()
{
    constructor(): this(UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now(), "", "", "")
}

data class BlogWithTags(
    @Embedded val blog: Blog,
    @Relation(
        parentColumn = "Id",
        entityColumn = "BlogId"
    )
    val tags: List<Tag>
)