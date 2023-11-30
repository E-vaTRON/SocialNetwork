package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import totechs.socialnetwork.Infrastructure.Services.TypeConvertorBase
import java.util.UUID

@ProvidedTypeConverter
class TypeConvertor : TypeConvertorBase()
{
}