package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import totechs.socialnetwork.Core.Application.ITagRepository

//Data Access Object
@Dao
interface ITagDAO : ITagRepository<Tag>
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun Insert(tag: Tag)

    @Delete
    override fun Delete(tag: Tag)

    @Query("SELECT * FROM Tag")
    override fun GetAll(): Flow<List<Tag>>

    @Query("SELECT * FROM Tag WHERE Id=:Id")
    override fun GetById(Id: String): Tag?
}