package totechs.socialnetwork.Core

import totechs.socialnetwork.Core.Application.IDomainHasCreatedOn
import totechs.socialnetwork.Core.Application.IDomainHasLastUpdatedOn
import java.time.LocalDateTime

open class EntityBase : EntityWithId<String>(), IDomainHasCreatedOn, IDomainHasLastUpdatedOn {
    override var Id: String = String()
    override var CreatedOn: LocalDateTime? = LocalDateTime.now()
    override var LastUpdatedOn: LocalDateTime? = null
    open var Deleted: Boolean = false

    init {
        Id = String()
    }
}