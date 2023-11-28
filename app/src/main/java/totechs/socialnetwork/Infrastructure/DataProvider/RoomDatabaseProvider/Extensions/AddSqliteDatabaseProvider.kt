package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddSqliteDatabaseProvider
{
    @Provides
    @Singleton
    fun addSqliteDatabase(application: Application): SqliteDataConext
    {
        return Room.databaseBuilder(context = application, klass = SqliteDataConext::class.java, name = "LocalDatabase")
            .addTypeConverter(TypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun addBlogRepository(sqliteDatabase: SqliteDataConext): IBlogDAO = sqliteDatabase.BlogDAO()

    @Provides
    @Singleton
    fun addTagRepository(sqliteDatabase: SqliteDataConext): ITagDAO = sqliteDatabase.TagDAO()
}