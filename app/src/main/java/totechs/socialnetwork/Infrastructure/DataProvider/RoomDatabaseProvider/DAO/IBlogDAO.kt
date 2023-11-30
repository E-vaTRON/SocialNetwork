package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import totechs.socialnetwork.Core.Application.IDataAccessObjectBase
import java.util.UUID

//Data Access Object
@Dao
interface IBlogDAO : IDataAccessObjectBase<Blog, UUID>
{
    @Query("DELETE FROM Blog WHERE Id = :id")
    suspend fun DeleteById(id: UUID)

    @Query("SELECT * FROM Blog")
    fun FindAll(): List<Blog>

    @Query("SELECT * FROM Blog WHERE id = :id LIMIT 1")
    fun FindById(id: UUID): Blog

    @Transaction
    @Query("SELECT * FROM Blog WHERE Id = :blogId")
    suspend fun getBlogWithTags(blogId: UUID): BlogTags
}