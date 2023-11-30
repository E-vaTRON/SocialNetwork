package totechs.socialnetwork.Core.Application

import java.util.UUID

interface IObjectMapper
{
    fun MapDomainToDatabase(entity: IDomainEntity): IDatabaseEntity
    fun MapDatabaseToDomain(entity: IDatabaseEntity): IDomainEntity
    fun MapUUIDToString(TDbId: UUID): String
    fun MapStringToUUID(TId: String): UUID
}