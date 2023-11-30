package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Embedded
import androidx.room.Relation

data class BlogTags(
    @Embedded val blog: Blog,
    @Relation(
        parentColumn = "Id",
        entityColumn = "BlogId"
    )
    val tags: List<Tag>
)