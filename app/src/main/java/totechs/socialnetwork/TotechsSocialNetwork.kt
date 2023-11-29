package totechs.socialnetwork

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import totechs.socialnetwork.Core.Application.IObjectMapper
import totechs.socialnetwork.Core.Application.Services.IDAOMapper
import totechs.socialnetwork.Core.Blog
import totechs.socialnetwork.Core.Tag
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.AddSqliteDatabaseProvider
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.BlogRepository
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.DAOMapper
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.ObjectMapper
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.SqliteDataConext
import java.time.LocalDateTime
import java.util.UUID

@HiltAndroidApp
class TotechsSocialNetwork : Application()
{
    val sqliteDatabase = AddSqliteDatabaseProvider
    lateinit var blogRepository: BlogRepository
    lateinit var objectMapper: IObjectMapper

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch()
        {
            val context: SqliteDataConext = sqliteDatabase.addSqliteDatabase(this@TotechsSocialNetwork)
            objectMapper = ObjectMapper()
            blogRepository = BlogRepository(context, objectMapper)

            //val newtag = Tag(UUID.randomUUID().toString(), false, LocalDateTime.now(), LocalDateTime.now(), "Name", "#FFFFFF")
            val newBlog = Blog(UUID.randomUUID().toString(), false, LocalDateTime.now(), LocalDateTime.now(), "Title", "Description", "ImageUrl",
                listOf( Tag(UUID.randomUUID().toString(), false, LocalDateTime.now(), LocalDateTime.now(), "Name", "#FFFFFF")))

            blogRepository.AddAsync(newBlog, Dispatchers.IO)
        }
    }
}