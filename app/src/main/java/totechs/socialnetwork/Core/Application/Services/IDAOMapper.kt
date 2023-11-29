package totechs.socialnetwork.Core.Application.Services

import totechs.socialnetwork.Core.Application.IDataAccessObjectBase
import totechs.socialnetwork.Core.Application.IDatabaseEntityWithId
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.SqliteDataConext

//interface IDAOMapper
//{
//    fun <TDatabaseEntity, TDbId> MapDAOWithEntity(context: SqliteDataConext, entity: TDatabaseEntity)
//    : IDataAccessObjectBase<IDatabaseEntityWithId<TDbId>, TDbId>
//    where TDatabaseEntity: IDatabaseEntityWithId<TDbId>
//}