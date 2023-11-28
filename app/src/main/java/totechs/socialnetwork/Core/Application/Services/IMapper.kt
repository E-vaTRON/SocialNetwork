package totechs.socialnetwork.Core.Application.Services

interface IMapper<TDomainEntity, TDatabaseEntity>
{
    fun mapToDbEntity(entity: TDomainEntity): TDatabaseEntity

    fun mapToEntity(dbEntity: TDatabaseEntity): TDomainEntity

    fun mapToDbEntities(entities: List<TDomainEntity>): List<TDatabaseEntity>
}