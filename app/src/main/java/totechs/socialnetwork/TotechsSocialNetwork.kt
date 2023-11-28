package totechs.socialnetwork

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.IBlogDAO
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.ITagDAO
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.Blog
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.Tag
import totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.AddSqliteDatabaseProvider
import java.time.LocalDateTime
import java.util.UUID

@HiltAndroidApp
class TotechsSocialNetwork : Application()
{
    val sqliteDatabase = AddSqliteDatabaseProvider
    lateinit var blogDao: IBlogDAO
    lateinit var tagDao: ITagDAO

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch()
        {
            blogDao = sqliteDatabase.addSqliteDatabase(this@TotechsSocialNetwork).BlogDAO()
            val blog = Blog(UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now(), "Title", "Description", "ImageUrl")
            blogDao.Insert(blog)

            tagDao = sqliteDatabase.addSqliteDatabase(this@TotechsSocialNetwork).TagDAO()
            val tag = Tag(UUID.randomUUID(), false, LocalDateTime.now(), LocalDateTime.now(), "Name", "#FFFFFF")
            tagDao.Insert(tag)
        }
    }
}