package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import totechs.socialnetwork.Infrastructure.EntityBase
import java.time.LocalDateTime
import java.util.UUID

@Parcelize
@Entity
data class Tag(
    @PrimaryKey
    override var Id: UUID,
    override var Deleted: Boolean,
    override var CreatedOn: LocalDateTime?,
    override var LastUpdatedOn: LocalDateTime?,
    val BlogId: UUID,
    val Name: String,
    val HexColor: String
): EntityBase(), Parcelable
{
    constructor(): this(UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID(), "", "")
}