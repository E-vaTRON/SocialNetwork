package totechs.socialnetwork.Core.Application

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.Blog

@Dao
interface IDataAccessObjectBase<TDatabaseEntity>
        where TDatabaseEntity : IDatabaseEntity
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Add(vararg obj: TDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddRange(obj: List<TDatabaseEntity>)

    @Update
    suspend fun Update(obj: TDatabaseEntity)

    @Delete
    suspend fun Delete(obj: TDatabaseEntity)

    @Delete
    suspend fun DeleteRange(vararg obj: TDatabaseEntity)

}