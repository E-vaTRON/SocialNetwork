package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import dev.krud.shapeshift.ShapeShiftBuilder
import totechs.socialnetwork.Core.Application.IDatabaseEntity
import totechs.socialnetwork.Core.Application.IDomainEntity
import totechs.socialnetwork.Core.Application.IObjectMapper
import java.util.UUID

class ObjectMapper : IObjectMapper
{
    lateinit var mapperBuilder : ShapeShiftBuilder

    init {
        mapperBuilder = ShapeShiftBuilder()
    }

    override fun MapDomainToDatabase(entity: IDomainEntity): IDatabaseEntity
    {
        return when (entity) {
            is DomainBlog -> BlogMapToDatabase.map<DomainBlog, DatabaseBlog>(entity) as  IDatabaseEntity
            is DomainTag -> TagMapToDatabase.map<DomainTag, DatabaseTag>(entity) as IDatabaseEntity
            else -> throw IllegalArgumentException("Unsupported entity type")
        }
    }

    override fun MapDatabaseToDomain(dbentity: IDatabaseEntity): IDomainEntity
    {
        if (dbentity is IDomainEntity)
        {
            throw IllegalArgumentException("Domain entities are not supported. Please pass Database entities.")
        }
        return when (dbentity) {
            is DatabaseBlog -> BlogMapToDomain.map<DatabaseBlog, DomainBlog>(dbentity)
            is DatabaseTag -> TagMapToDomain.map<DatabaseTag, DomainTag>(dbentity)
            else -> throw IllegalArgumentException("Unsupported entity type")
        }
    }

    fun MapDatabaseToDomainWithInclude(dbentity: IDatabaseEntity, includeEntity: IDatabaseEntity): IDomainEntity
    {
        if (dbentity is IDomainEntity || includeEntity is IDomainEntity)
        {
            throw IllegalArgumentException("Domain entities are not supported. Please pass Database entities.")
        }
        return when (dbentity) {
            is DatabaseBlog ->
            {
                var entity = BlogMapToDomain.map<DatabaseBlog, DomainBlog>(dbentity)
                if (includeEntity is DatabaseTag)
                    entity.Tags.add(MapDatabaseToDomain(includeEntity) as DomainTag)
                else throw IllegalArgumentException("Included entity must be a Database Tag")
                entity
            }
            is DatabaseTag -> TagMapToDomain.map<DatabaseTag, DomainTag>(dbentity)
            else -> throw IllegalArgumentException("Unsupported entity type")
        }
    }

    override fun MapUUIDToString(TDbId: UUID): String
    {
        val MapUUIDToString = mapperBuilder.withMapping<UUID, String>(){}.build()
        return MapUUIDToString.map(TDbId)
    }

    override fun MapStringToUUID(TId: String): UUID
    {
        val MapUUIDToString = mapperBuilder.withMapping<String, UUID>(){}.build()
        return MapUUIDToString.map(TId)
    }

    val BlogMapToDatabase = mapperBuilder.withMapping<DomainBlog, DatabaseBlog>
    {
        DomainBlog::Id mappedTo DatabaseBlog::Id withTransformer { (originalValue) -> UUID.fromString(originalValue) }
        DomainBlog::Deleted mappedTo DatabaseBlog::Deleted
        DomainBlog::CreatedOn mappedTo DatabaseBlog::CreatedOn
        DomainBlog::LastUpdatedOn mappedTo DatabaseBlog::LastUpdatedOn
        DomainBlog::Title mappedTo DatabaseBlog::Title
        DomainBlog::Description mappedTo DatabaseBlog::Description
        DomainBlog::ImageUrl mappedTo DatabaseBlog::ImageUrl
        DomainBlog::Deleted mappedTo DatabaseBlog::Deleted
    }.build()

    val BlogMapToDomain = mapperBuilder.withMapping<DatabaseBlog, DomainBlog>
    {
        DatabaseBlog::Id mappedTo DomainBlog::Id withTransformer { (originalValue) -> originalValue.toString() }
        DatabaseBlog::Deleted mappedTo DomainBlog::Deleted
        DatabaseBlog::CreatedOn mappedTo DomainBlog::CreatedOn
        DatabaseBlog::LastUpdatedOn mappedTo DomainBlog::LastUpdatedOn
        DatabaseBlog::Title mappedTo DomainBlog::Title
        DatabaseBlog::Description mappedTo DomainBlog::Description
        DatabaseBlog::ImageUrl mappedTo DomainBlog::ImageUrl
        DatabaseBlog::Deleted mappedTo DomainBlog::Deleted
    }.build()

    val TagMapToDatabase = mapperBuilder.withMapping<DomainTag, DatabaseTag>
    {
        DomainTag::Id mappedTo DatabaseTag::Id
        DomainTag::Deleted mappedTo DatabaseTag::Deleted
        DomainTag::CreatedOn mappedTo DatabaseTag::CreatedOn
        DomainTag::LastUpdatedOn mappedTo DatabaseTag::LastUpdatedOn
        DomainTag::Name mappedTo DatabaseTag::Name
        DomainTag::HexColor mappedTo DatabaseTag::HexColor
    }.build()

    val TagMapToDomain = mapperBuilder.withMapping<DatabaseTag, DomainTag>
    {
        DatabaseTag::Id mappedTo DomainTag::Id
        DatabaseTag::Deleted mappedTo DomainTag::Deleted
        DatabaseTag::CreatedOn mappedTo DomainTag::CreatedOn
        DatabaseTag::LastUpdatedOn mappedTo DomainTag::LastUpdatedOn
        DatabaseTag::Name mappedTo DomainTag::Name
        DatabaseTag::HexColor mappedTo DomainTag::HexColor
    }.build()
}