package totechs.socialnetwork.Core.Application

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface IDataAccessObjectBase<TDatabaseEntity, TDbId>
        where TDatabaseEntity : IDatabaseEntityWithId<TDbId>
{
//    @Query("SELECT * FROM EntityBase")
//    fun FindAll(): List<TDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Add(vararg entity: TDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddRange(entities: List<out TDatabaseEntity>)

    @Update
    suspend fun Update(entity: TDatabaseEntity)

    @Delete
    suspend fun Delete(entity: TDatabaseEntity)

    @Delete
    suspend fun DeleteRange(vararg entity: TDatabaseEntity)
}