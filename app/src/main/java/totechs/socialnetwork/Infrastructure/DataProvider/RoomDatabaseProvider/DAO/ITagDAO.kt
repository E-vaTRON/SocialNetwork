package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Dao
import androidx.room.Query
import totechs.socialnetwork.Core.Application.IDataAccessObjectBase
import java.util.UUID

//Data Access Object
@Dao
interface ITagDAO : IDataAccessObjectBase<Tag, UUID>
{
    @Query("DELETE FROM Tag WHERE Id = :id")
    suspend fun DeleteById(id: UUID)

    @Query("SELECT * FROM Tag")
    fun FindAll(): List<Tag>

    @Query("SELECT * FROM Tag WHERE id = :id LIMIT 1")
    fun FindById(id: UUID): Tag

    @Query("SELECT * FROM Tag WHERE BlogId = :blogId")
    fun GetTagsForBlog(blogId: UUID): List<Tag>
}