package totechs.socialnetwork.Core

import totechs.socialnetwork.Core.Application.IDatabaseHasLastUpdatedOn
import totechs.socialnetwork.Core.Application.IDomainHasCreatedOn
import java.time.LocalDateTime

open class EntityBase : EntityWithId<String>(), IDomainHasCreatedOn, IDatabaseHasLastUpdatedOn {
    override var Id: String = String()
    override var CreatedOn: LocalDateTime? = LocalDateTime.now()
    override var LastUpdatedOn: LocalDateTime? = null
    open var Deleted: Boolean = false

    init {
        Id = String()
    }
}