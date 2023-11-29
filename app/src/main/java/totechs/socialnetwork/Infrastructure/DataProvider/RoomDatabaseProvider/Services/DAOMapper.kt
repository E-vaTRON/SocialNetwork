package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import totechs.socialnetwork.Core.Application.IDataAccessObjectBase
import totechs.socialnetwork.Core.Application.IDatabaseEntity
import totechs.socialnetwork.Core.Application.IDatabaseEntityWithId
import totechs.socialnetwork.Core.Application.IDomainEntityWithId
import totechs.socialnetwork.Infrastructure.EntityBase
import java.util.UUID

//class DAOMapper : IDAOMapper
//{
//    override fun <TDatabaseEntity, TDbId> MapDAOWithEntity(context: SqliteDataConext, entity: TDatabaseEntity)
//        : IDataAccessObjectBase<IDatabaseEntityWithId<TDbId>, TDbId>
//    where TDatabaseEntity: IDatabaseEntityWithId<TDbId>
//    {
//        return when (entity)
//        {
//            is DatabaseBlog -> context.BlogDAO() as IDataAccessObjectBase<IDatabaseEntityWithId<TDbId>, TDbId>
//            is DatabaseTag -> context.TagDAO() as IDataAccessObjectBase<IDatabaseEntityWithId<TDbId>, TDbId>
//            else -> throw IllegalArgumentException("Unsupported entity type")
//        }
//    }
//}