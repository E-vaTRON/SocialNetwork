package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Blog::class, Tag::class], version = 1)
@TypeConverters(TypeConvertor::class)
abstract class SqliteDataConext : RoomDatabase()
{
    abstract fun BlogDAO(): IBlogDAO
    abstract fun TagDAO(): ITagDAO
}