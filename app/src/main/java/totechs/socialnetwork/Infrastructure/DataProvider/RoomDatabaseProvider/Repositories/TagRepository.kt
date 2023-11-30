package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import totechs.socialnetwork.Core.Application.ITagRepository
import totechs.socialnetwork.Core.Application.IObjectMapper
import kotlin.coroutines.CoroutineContext

class TagRepository: ITagRepository
{
    var DataContext : SqliteDataConext
    var ObjectMapper : IObjectMapper
    var DAO : ITagDAO

    constructor(conext: SqliteDataConext,
                objectMapper : IObjectMapper)
    {
        DataContext = conext
        ObjectMapper = objectMapper
        DAO = DataContext.TagDAO()
    }

     suspend fun InternalFindByIdAsync(id: String, coroutineContext: CoroutineContext)
            : DatabaseTag? = coroutineScope()
    {
        val entity = async(Dispatchers.IO)
        {
            DAO.FindById(ObjectMapper.MapStringToUUID(id))
        }.await()
        entity
    }

    override suspend fun FindAllAsync(predicate: ((DomainTag) -> Boolean)?,coroutineContext: CoroutineContext)
            : List<DomainTag> = coroutineScope()
    {
        val dbEntities = async(Dispatchers.IO) { DAO.FindAll() }.await()

        val results = if (predicate != null) {
            dbEntities.filter()
            {
                val domainEntity = ObjectMapper.MapDatabaseToDomain(it)
                if (domainEntity is DomainTag) {
                    predicate.invoke(domainEntity)
                } else { false}
            }
        } else { dbEntities }
        results as List<DomainTag>
    }

    override suspend fun FindByIdAsync(id: String, coroutineContext: CoroutineContext)
        : DomainTag?
    {
        val dbEntity = InternalFindByIdAsync(id, coroutineContext)
        return if (dbEntity != null)
            ObjectMapper.MapDatabaseToDomain(dbEntity) as DomainTag
        else null
    }


    override suspend fun AddAsync(entity: DomainTag, coroutineContext: CoroutineContext)
    {
        coroutineScope()
        {
            launch(coroutineContext)
            {
                val dbEntity = ObjectMapper.MapDomainToDatabase(entity) as DatabaseTag
                async(Dispatchers.IO)
                {
                    DAO.Add(dbEntity)
                }.await()
            }
        }
    }

    override suspend fun AddRangeAsync(entities: List<DomainTag>, coroutineContext: CoroutineContext)
    {
        coroutineScope()
        {
            launch(coroutineContext)
            {
                val dbEntities = entities.map()
                {
                    ObjectMapper.MapDomainToDatabase(it) as DatabaseTag
                }
                async(Dispatchers.IO)
                {
                    DAO.AddRange(dbEntities)
                }.await()
            }
        }
    }

    override suspend fun AddRangeAsync(vararg entities: DomainTag, coroutineContext: CoroutineContext)
            = AddRangeAsync(entities.toList(), coroutineContext)

    override suspend fun UpdateAsync(entity: DomainTag, coroutineContext: CoroutineContext)
    {
        coroutineScope()
        {
            launch(coroutineContext)
            {
                val dbEntity = async(Dispatchers.IO)
                {
                    InternalFindByIdAsync(entity.Id, coroutineContext)
                }.await()

                if (dbEntity != null)
                {
                    val updatedEntity = ObjectMapper.MapDomainToDatabase(entity) as DatabaseTag
                    async(Dispatchers.IO) {
                        DAO.Update(updatedEntity)
                    }.await()
                }
                else
                {
                    println("Entity with ID ${entity.Id} does not exist and cannot be updated.")
                }
            }
        }
    }

    override suspend fun DeleteAsync(entity: DomainTag, coroutineContext: CoroutineContext)
            = DeleteByIdAsync(entity.Id, coroutineContext)

    override suspend fun DeleteByIdAsync(id: String, coroutineContext: CoroutineContext)
    {
        coroutineScope()
        {
            launch(coroutineContext)
            {
                val dbEntity = async(Dispatchers.IO)
                {
                    InternalFindByIdAsync(id, coroutineContext)
                }.await()

                if (dbEntity != null)
                {
                    async(Dispatchers.IO)
                    {
                        DAO.DeleteById(ObjectMapper.MapStringToUUID(id))
                    }.await()
                } else {
                    println("Entity with ID $id does not exist and cannot be deleted.")
                }
            }
        }
    }

    override suspend fun DeleteRangeAsync(entities: List<DomainTag>, coroutineContext: CoroutineContext)
    {
        coroutineScope()
        {
            launch(coroutineContext)
            {
                entities.forEach()
                { entity ->
                    val foundEntity = InternalFindByIdAsync(entity.Id, coroutineContext)
                    if (foundEntity == null)
                    {
                        println("Entity with ID ${entity.Id} does not exist and cannot be deleted.")
                    }
                }
                val dbEntities = entities.map()
                {
                    ObjectMapper.MapDomainToDatabase(it) as DatabaseTag
                }
                async(Dispatchers.IO) {
                    DAO.DeleteRange(dbEntities)
                }.await()
            }
        }
    }

    override suspend fun DeleteRangeAsync( vararg entities: DomainTag, coroutineContext: CoroutineContext)
            = DeleteRangeAsync(entities.toList(), coroutineContext)
}