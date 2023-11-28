package totechs.socialnetwork.Infrastructure

import totechs.socialnetwork.Core.Application.IDatabaseHasCreatedOn
import totechs.socialnetwork.Core.Application.IDatabaseHasLastUpdatedOn
import java.time.LocalDateTime
import java.util.UUID

open class EntityBase : EntityWithId<UUID>(), IDatabaseHasCreatedOn, IDatabaseHasLastUpdatedOn {
    override var Id: UUID = UUID.randomUUID()
    override var CreatedOn: LocalDateTime? = LocalDateTime.now()
    override var LastUpdatedOn: LocalDateTime? = null
    open var Deleted: Boolean = false

    init {
        Id = UUID.randomUUID()
    }
}