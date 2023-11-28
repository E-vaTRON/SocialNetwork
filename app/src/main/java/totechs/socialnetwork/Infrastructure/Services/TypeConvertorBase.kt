package totechs.socialnetwork.Infrastructure.Services

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

@ProvidedTypeConverter
open class TypeConvertorBase {
    @TypeConverter
    open fun UUIDToString(id: UUID): String
    {
        return id.toString()
    }

    @TypeConverter
    open fun StringToUUID(id: String): UUID {
        return UUID.fromString(id)
    }

    @TypeConverter
    open fun TimestampToDateTime(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC) }
    }

    @TypeConverter
    open fun DateTimeToTimestamp(date: LocalDateTime?): Long? {
        return date?.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
    }
}