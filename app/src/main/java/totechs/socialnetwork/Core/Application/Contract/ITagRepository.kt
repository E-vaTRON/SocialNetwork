package totechs.socialnetwork.Core.Application

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import totechs.socialnetwork.Core.Tag

interface ITagRepository<TEntity>
{
    fun Insert(tag: TEntity)

    fun Delete(tag: TEntity)

    fun GetAll(): Flow<List<TEntity>>

    fun GetById(Id: String): TEntity?
}