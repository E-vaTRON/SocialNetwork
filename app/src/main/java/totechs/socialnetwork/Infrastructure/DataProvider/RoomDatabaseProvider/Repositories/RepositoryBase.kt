package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import totechs.socialnetwork.Core.Application.IDatabaseEntityWithId
import totechs.socialnetwork.Core.Application.IDomainEntityWithId
import totechs.socialnetwork.Core.Application.IDatabaseRepositoryBase
import totechs.socialnetwork.Core.Application.IObjectMapper
import java.util.UUID
import kotlin.coroutines.CoroutineContext

public abstract class DatabaseRepositoryBaseWithId<TDomainEntity, TId, TDatabaseEntity, TDbId>
    : IDatabaseRepositoryBase<TDomainEntity, TId>
where TDomainEntity : IDomainEntityWithId<TId>,
      TDatabaseEntity : IDatabaseEntityWithId<TDbId>
{
    protected var DbContext: SqliteDataConext
    protected open var ObjectMapper : IObjectMapper

    constructor(dbContext: SqliteDataConext,
                objectMapper : IObjectMapper)
    {
        DbContext = dbContext
        ObjectMapper = objectMapper
    }

//    override suspend fun AddAsync(entity: TDomainEntity, coroutineContext: CoroutineContext)
//    {
//        coroutineScope {
//            launch(coroutineContext) {
//                val dbEntity = ObjectMapper.MapDomainToDatabase(entity)
//                val dao = DAOMapper.MapDAOWithEntity(DbContext, dbEntity)
//                val check = async(Dispatchers.IO)
//                {
//                    dao.Add(dbEntity)
//                }.await()
//                if (check != null)
//                {
//                    val hello = "added error"
//                }
//            }
//        }
//    }

//    override suspend fun AddRangeAsync(vararg entities: TDomainEntity, coroutineContext: CoroutineContext)
//            = AddRangeAsync(entities.toList(), coroutineContext)
//
//    override suspend fun AddRangeAsync(entities: List<TDomainEntity>, coroutineContext: CoroutineContext)
//    {
//        coroutineScope {
//            launch(coroutineContext) {
//                val dbEntities = entities.map { ObjectMapper.MapDomainToDatabase(it) }
//                val dao = DAOMapper.MapDAOWithEntity(DbContext, dbEntities[0])
//                dao.AddRange(dbEntities)
//            }
//        }
//    }
//
//    override suspend fun UpdateAsync(entity: TDomainEntity, coroutineContext: CoroutineContext)
//    {
//        coroutineScope {
//            launch(coroutineContext) {
//                val dbEntity = ObjectMapper.MapDomainToDatabase(entity)
//                val dao = DAOMapper.MapDAOWithEntity(DbContext, dbEntity)
//                dao.Update(dbEntity)
//            }
//        }
//
//    }

//    override suspend fun DeleteAsync(entity: TDomainEntity, coroutineContext: CoroutineContext)
//            = deleteByIdAsync(entity.Id, coroutineContext)

//    open suspend fun deleteByIdAsync(id: TId, coroutineContext: CoroutineContext) {
//        val disableQuickFind = false
//        val dbEntity = internalFindByIdAsync(id, disableQuickFind, coroutineContext)
//        if (dbEntity == null) return
//        //DbContext.remove(dbEntity)
//        //DbContext.saveChangesAsync(cancellationToken)
//    }

//    override suspend fun DeleteRangeAsync(entities: List<TDomainEntity>, coroutineContext: CoroutineContext) {
//    }
//
//    override suspend fun DeleteRangeAsync(vararg entities: TDomainEntity, coroutineContext: CoroutineContext)
//            = DeleteRangeAsync(entities.toList(), coroutineContext)

}

abstract class RepositoryBase<TDomainEntity, TDatabaseEntity>(context: SqliteDataConext, mapper: IObjectMapper)
    : DatabaseRepositoryBaseWithId<TDomainEntity, String, TDatabaseEntity, UUID>(context, mapper)
        where TDomainEntity : IDomainEntityWithId<String>,
              TDatabaseEntity : IDatabaseEntityWithId<UUID>