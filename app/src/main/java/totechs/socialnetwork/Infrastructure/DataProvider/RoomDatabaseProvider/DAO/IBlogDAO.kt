package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import totechs.socialnetwork.Core.Application.IDataAccessObjectBase
import java.util.UUID

//Data Access Object
@Dao
interface IBlogDAO : IDataAccessObjectBase<Blog>
{
    @Query("DELETE FROM Blog WHERE Id = :id")
    suspend fun DeleteById(id: UUID)

    @Query("SELECT * FROM Blog")
    fun FindAll(): Flow<List<Blog>>

    @Query("SELECT * FROM Blog WHERE id = :id LIMIT 1")
    fun FindById(id: Int): Flow<Blog>
}