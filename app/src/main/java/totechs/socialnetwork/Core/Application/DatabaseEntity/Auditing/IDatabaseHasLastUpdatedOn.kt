package totechs.socialnetwork.Core.Application

import java.time.LocalDateTime

interface IDatabaseHasLastUpdatedOn
{
    val LastUpdatedOn: LocalDateTime?
}