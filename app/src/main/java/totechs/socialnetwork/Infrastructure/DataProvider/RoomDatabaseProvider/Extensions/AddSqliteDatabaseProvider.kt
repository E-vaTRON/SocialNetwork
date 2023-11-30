package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import totechs.socialnetwork.Core.Application.IBlogRepository
import totechs.socialnetwork.Core.Application.IObjectMapper
import totechs.socialnetwork.Core.Application.ITagRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddSqliteDatabaseProvider
{
    @Provides
    fun AddObjectMapper(): IObjectMapper = ObjectMapper()

    @Provides
    @Singleton
    fun AddSqliteDatabase(application: Application): SqliteDataConext
    {
        return Room.databaseBuilder(context = application, klass = SqliteDataConext::class.java, name = "LocalDatabase")
            .addTypeConverter(TypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun AddBlogDAO(sqliteDatabase: SqliteDataConext): IBlogDAO = sqliteDatabase.BlogDAO()

    @Provides
    @Singleton
    fun AddTagDAO(sqliteDatabase: SqliteDataConext): ITagDAO = sqliteDatabase.TagDAO()

    @Provides
    @Singleton
    fun AddBlogRepository(sqliteDatabase: SqliteDataConext, mapper: IObjectMapper): IBlogRepository
    {
        return BlogRepository(sqliteDatabase, mapper)
    }

    @Provides
    @Singleton
    fun AddTagRepository(sqliteDatabase: SqliteDataConext, mapper: IObjectMapper): ITagRepository
    {
        return TagRepository(sqliteDatabase, mapper)
    }
}