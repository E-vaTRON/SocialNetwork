package totechs.socialnetwork.Core

import java.time.LocalDateTime

data class Tag(
    override var Id: String,
    override var Deleted: Boolean,
    override var CreatedOn: LocalDateTime?,
    override var LastUpdatedOn: LocalDateTime?,
    val Name: String,
    val HexColor: String
): EntityBase()