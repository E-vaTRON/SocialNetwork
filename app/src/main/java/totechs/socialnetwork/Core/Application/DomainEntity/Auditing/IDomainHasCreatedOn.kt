package totechs.socialnetwork.Core.Application

import java.time.LocalDateTime

interface IDomainHasCreatedOn
{
    val CreatedOn: LocalDateTime?
}