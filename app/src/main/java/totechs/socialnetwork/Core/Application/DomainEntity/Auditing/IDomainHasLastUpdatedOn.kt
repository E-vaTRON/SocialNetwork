package totechs.socialnetwork.Core.Application

import java.time.LocalDateTime

interface IDomainHasLastUpdatedOn
{
    val LastUpdatedOn: LocalDateTime?
}