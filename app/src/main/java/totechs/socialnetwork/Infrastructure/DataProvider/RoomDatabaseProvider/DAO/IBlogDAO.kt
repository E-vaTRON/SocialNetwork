package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import totechs.socialnetwork.Core.Application.IBlogRepository

//Data Access Object
@Dao
interface IBlogDAO : IBlogRepository<Blog>
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun Insert(blog: Blog)

    @Delete
    override fun Delete(blog: Blog)

    @Query("SELECT * FROM Blog")
    override fun GetAll(): Flow<List<Blog>>

    @Query("SELECT * FROM Blog WHERE Id=:Id")
    override fun GetById(Id: String): Blog?
}