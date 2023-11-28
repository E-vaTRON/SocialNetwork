package totechs.socialnetwork.Infrastructure.BaseRepository

import totechs.socialnetwork.Core.Application.IDatabaseEntityWithId
import totechs.socialnetwork.Core.Application.IDomainEntityWithId
import totechs.socialnetwork.Core.Application.IRepository
import java.time.LocalDate


public abstract class RepositoryBaseWithId<TDomainEntity, TId, TDatabaseEntity, TDbId>: IRepository<TDomainEntity, TId>
where TDomainEntity : IDomainEntityWithId<TId>,
      TDatabaseEntity : IDatabaseEntityWithId<TDbId>
{
    protected lateinit var DatabaseModel: TDatabaseEntity
    protected lateinit var DomainModel: TDomainEntity
    //protected lateinit var DbContext: PathogenDbContext
    //protected lateinit var DbSet: DbSet<TDbEntity>

//    constructor(context: IContext, databaseModel: TDatabaseEntity, domainModel: TDomainEntity) {
//        DatabaseModel = databaseModel
//        DomainModel = domainModel
//        //DbContext = context
//        //DbSet = DbContext.set<TDbEntity>()
//    }
//
//    open suspend fun findAllAsync(predicate: Expression<Func<TDomainEntity, Boolean>>? = null, cancellationToken: CancellationToken = default): List<TEntity> {
//        val query = getQueryableAsync(false, cancellationToken)
//        return query.whereIf(predicate != null, predicate!!).toList(cancellationToken)
//    }
//
//    open suspend fun findByIdAsync(id: TId, cancellationToken: CancellationToken = default, isQuickFind: Boolean = true): TEntity? {
//        val dbEntity = internalFindByIdAsync(id, isQuickFind, cancellationToken)
//        return mapToEntity(dbEntity)
//    }
//
//    open suspend fun addAsync(entity: TDomainEntity, cancellationToken: CancellationToken = default) {
//        val dbEntity = mapToDbEntity(entity)
//        requireNotNull(dbEntity) { "dbEntity must not be null" }
//        //DbSet.addAsync(dbEntity, cancellationToken)
//        //DbContext.saveChangesAsync(cancellationToken)
//    }
//
//    open suspend fun addRangeAsync(entities: List<TDomainEntity>, cancellationToken: CancellationToken = default) {
//        val dbEntities = mapToDbEntities(entities)
//        //DbSet.addAllAsync(dbEntities, cancellationToken)
//        //DbContext.saveChangesAsync(cancellationToken)
//    }
//
//    open suspend fun addRangeAsync(cancellationToken: CancellationToken = default, vararg entities: TEntity)
//            = addRangeAsync(entities.toList(), cancellationToken)
//
//    open suspend fun updateAsync(entity: TDomainEntity, cancellationToken: CancellationToken = default) {
//        val disableQuickFind = false
//        val dbEntity = internalFindByIdAsync(entity.id, disableQuickFind, cancellationToken)
//        //Mapper.map(entity, dbEntity)
//        //DbContext.saveChangesAsync(cancellationToken)
//    }
//
//    open suspend fun deleteAsync(entity: TDomainEntity, cancellationToken: CancellationToken = default)
//            = deleteByIdAsync(entity.id, cancellationToken)
//
//    open suspend fun deleteByIdAsync(id: TId, cancellationToken: CancellationToken = default) {
//        val disableQuickFind = false
//        val dbEntity = internalFindByIdAsync(id, disableQuickFind, cancellationToken)
//        if (dbEntity == null) return
//        //DbContext.remove(dbEntity)
//        //DbContext.saveChangesAsync(cancellationToken)
//    }
//
//    open suspend fun deleteRangeAsync(entities: List<TDomainEntity>, cancellationToken: CancellationToken = default) {
//        val ids = entities.map { parseId(it.id) }
//        //DbContext.removeRange(DbSet.filter { it.id in ids })
//        //DbContext.saveChangesAsync(cancellationToken)
//    }
//
//    open suspend fun deleteRangeAsync(cancellationToken: CancellationToken = default, vararg entities: TEntity)
//            = deleteRangeAsync(entities.toList(), cancellationToken)
//
//    protected open suspend fun internalFindByIdAsync(id: TId, asNoTracking: Boolean = true, cancellationToken: CancellationToken = default): TDbEntity? {
//        val dbId = parseId(id)
//        var query = DbSet.asQueryable()
//        if (asNoTracking)
//            query = query.asNoTracking()
//
//        val dbEntity = query.firstOrNull { it.id == dbId }
//        return dbEntity
//    }
//    protected open fun parseId(id: TId): TDbId = Mapper.map<TDbId>(id)
//
//    protected open suspend fun getQueryableAsync(cancellationToken: CancellationToken = default): List<TEntity>
//            = getQueryableAsync(false, cancellationToken)
//
//    protected open suspend fun getQueryableAsync(asNoTracking: Boolean = false, cancellationToken: CancellationToken = default): List<TEntity> {
//        var query = DbSet.asQueryable()
//        if (asNoTracking)
//            query = query.asNoTracking()
//
//        return query.projectTo<TDomainEntity>(Mapper.configurationProvider)
//    }

//    protected open fun mapToDatabaseModel(entity: Any): TDatabaseEntity?
//            = Mapper.map(entity, Class<TDatabaseEntity>())
//
//
//    protected open fun mapToEntityModel(dbEntity: TDatabaseEntity?): TDomainEntity?
//            = Mapper.map<TDatabaseEntity, TDomainEntity?>(dbEntity)
//
//    protected open fun mapToDbEntities(entities: List<TDomainEntity?>): List<TDatabaseEntity>
//            = Mapper.map<List<TDomainEntity?>, List<TDatabaseEntity>>(entities)
}

//abstract class RepositoryBase<TDomainEntity, TDatabaseEntity>(context: string, mapper: IMapper)
//    : RepositoryBaseWithId<TDomainEntity, String, TDatabaseEntity, UUID>(context, mapper)
//        where TDomainEntity : IDomainEntityWithId<String>,
//              TDatabaseEntity : IDatabaseEntityWithId<UUID>