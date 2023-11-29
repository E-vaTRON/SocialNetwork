package totechs.socialnetwork.Core.Application

interface IObjectMapper
{
    fun MapDomainToDatabase(entity: IDomainEntity): IDatabaseEntity
    fun MapDatabaseToDomain(entity: IDatabaseEntity): IDomainEntity
}