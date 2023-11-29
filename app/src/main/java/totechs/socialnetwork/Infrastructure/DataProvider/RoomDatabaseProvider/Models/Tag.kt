package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Entity
import androidx.room.PrimaryKey
import totechs.socialnetwork.Infrastructure.EntityBase
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class Tag(
    @PrimaryKey
    override var Id: UUID,
    override var Deleted: Boolean,
    override var CreatedOn: LocalDateTime?,
    override var LastUpdatedOn: LocalDateTime?,
    val Name: String,
    val HexColor: String
): EntityBase()
{
    constructor(): this(UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now(), "", "")
}