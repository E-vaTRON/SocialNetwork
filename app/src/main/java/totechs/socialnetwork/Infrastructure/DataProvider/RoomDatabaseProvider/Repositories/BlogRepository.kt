package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import totechs.socialnetwork.Core.Application.IBlogRepository
import totechs.socialnetwork.Core.Application.IObjectMapper
import kotlin.coroutines.CoroutineContext

class BlogRepository : IBlogRepository
{
    var DataContext : SqliteDataConext
    var ObjectMapper : IObjectMapper
    var DAO : IBlogDAO

    constructor(conext: SqliteDataConext,
                objectMapper : IObjectMapper)
    {
        DataContext = conext
        ObjectMapper = objectMapper
        DAO = DataContext.BlogDAO()
    }

    suspend fun InternalFindByIdAsync(id: String, coroutineContext: CoroutineContext)
            : DatabaseBlog? = coroutineScope()
    {
        val entity = async(Dispatchers.IO)
        {
            DAO.FindById(ObjectMapper.MapStringToUUID(id))
        }.await()
        entity
    }

    override suspend fun FindAllAsync(predicate: ((DomainBlog) -> Boolean)?,coroutineContext: CoroutineContext)
        : List<DomainBlog> = coroutineScope()
    {
        val dbEntities = async(Dispatchers.IO) { DAO.FindAll() }.await()
        val entities = dbEntities.map { ObjectMapper.MapDatabaseToDomain(it) as DomainBlog }

        val results = if (predicate != null) {
            entities.filter { predicate.invoke(it) }
        } else { entities }

        results
    }

    override suspend fun FindByIdAsync(id: String, coroutineContext: CoroutineContext)
        : DomainBlog?
    {
        val dbEntity = InternalFindByIdAsync(id, coroutineContext)
        return if (dbEntity != null)
            ObjectMapper.MapDatabaseToDomain(dbEntity) as DomainBlog
        else null
    }

    override suspend fun AddAsync(entity: DomainBlog, coroutineContext: CoroutineContext)
    {
        coroutineScope {
            launch(coroutineContext) {
                val dbEntity = ObjectMapper.MapDomainToDatabase(entity) as DatabaseBlog
                async(Dispatchers.IO)
                {
                    DAO.Add(dbEntity)
                }.await()
            }
        }
    }

    override suspend fun AddRangeAsync(entities: List<DomainBlog>, coroutineContext: CoroutineContext)
    {
        coroutineScope {
            launch(coroutineContext) {
                val dbEntities = entities.map()
                {
                    ObjectMapper.MapDomainToDatabase(it) as DatabaseBlog
                }
                async(Dispatchers.IO)
                {
                    DAO.AddRange(dbEntities)
                }.await()
            }
        }
    }

    override suspend fun AddRangeAsync(vararg entities: DomainBlog, coroutineContext: CoroutineContext)
        = AddRangeAsync(entities.toList(), coroutineContext)

    override suspend fun UpdateAsync(entity: DomainBlog, coroutineContext: CoroutineContext)
    {
        coroutineScope {
            launch(coroutineContext) {
                val dbEntity = async(Dispatchers.IO) {
                    InternalFindByIdAsync(entity.Id, coroutineContext)
                }.await()

                if (dbEntity != null) {
                    val updatedEntity = ObjectMapper.MapDomainToDatabase(entity) as DatabaseBlog
                    async(Dispatchers.IO) {
                        DAO.Update(updatedEntity)
                    }.await()
                } else {
                    println("Entity with ID ${entity.Id} does not exist and cannot be updated.")
                }
            }
        }
    }

    override suspend fun DeleteAsync(entity: DomainBlog, coroutineContext: CoroutineContext)
        = DeleteByIdAsync(entity.Id, coroutineContext)

    override suspend fun DeleteByIdAsync(id: String, coroutineContext: CoroutineContext)
    {
        coroutineScope {
            launch(coroutineContext) {
                val dbEntity = async(Dispatchers.IO) {
                    InternalFindByIdAsync(id, coroutineContext)
                }.await()

                if (dbEntity != null) {
                    async(Dispatchers.IO) {
                        DAO.DeleteById(ObjectMapper.MapStringToUUID(id))
                    }.await()
                } else {
                    println("Entity with ID $id does not exist and cannot be deleted.")
                }
            }
        }
    }

    override suspend fun DeleteRangeAsync(entities: List<DomainBlog>, coroutineContext: CoroutineContext)
    {
        coroutineScope {
            launch(coroutineContext) {
                entities.forEach { entity ->
                    val foundEntity = InternalFindByIdAsync(entity.Id, coroutineContext)
                    if (foundEntity == null) {
                        println("Entity with ID ${entity.Id} does not exist and cannot be deleted.")
                    }
                }

                val dbEntities = entities.map()
                {
                    ObjectMapper.MapDomainToDatabase(it) as DatabaseBlog
                }
                async(Dispatchers.IO) {
                    DAO.DeleteRange(dbEntities)
                }.await()
            }
        }
    }

    override suspend fun DeleteRangeAsync( vararg entities: DomainBlog, coroutineContext: CoroutineContext)
        = DeleteRangeAsync(entities.toList(), coroutineContext)

    suspend fun AddTagToBlogByIdAsync(blogId: String, tag: DomainTag, coroutineContext: CoroutineContext) {
        coroutineScope {
            launch(coroutineContext) {
                // Convert the blog ID to a UUID
                val id = ObjectMapper.MapStringToUUID(blogId)

                // Find the blog
                var dbBlog = async(Dispatchers.IO) {
                    DataContext.BlogDAO().FindById(id)
                }.await()

                // If the blog exists, add the tag
                if (dbBlog != null) {
                    val blog = ObjectMapper.MapDatabaseToDomain(dbBlog) as DomainBlog
                    blog.Tags.add(tag)
                    dbBlog = ObjectMapper.MapDomainToDatabase(blog) as DatabaseBlog
                    // Update the blog
                    async(Dispatchers.IO) {
                        DataContext.BlogDAO().Update(dbBlog)
                    }.await()
                } else {
                    // Handle the case where the blog does not exist
                    println("Blog with ID $blogId does not exist and cannot be updated.")
                }
            }
        }
    }

    suspend fun UpdateBlogWithAsync(entity: DomainBlog, coroutineContext: CoroutineContext)
    {
        coroutineScope {
            launch(coroutineContext) {
                val dbEntity = async(Dispatchers.IO) {
                    InternalFindByIdAsync(entity.Id, coroutineContext)
                }.await()

                if (dbEntity != null) {
                    val updatedEntity = ObjectMapper.MapDomainToDatabase(entity) as DatabaseBlog
                    async(Dispatchers.IO) {
                        DAO.Update(updatedEntity)
                        val currentTags = DataContext.TagDAO().GetTagsForBlog(dbEntity.Id).map()
                        {
                            ObjectMapper.MapDatabaseToDomain(it) as DomainTag
                        }
                        val tagsToAdd = entity.Tags.subtract(currentTags)
                        val tagsToRemove = currentTags.subtract(entity.Tags)

                        // Remove the tags that are not in the new list of tags
                        tagsToRemove.forEach { tag ->
                            val dbTag = ObjectMapper.MapDomainToDatabase(tag) as DatabaseTag
                            DataContext.TagDAO().Delete(dbTag)
                        }

                        // Add the new tags
                        tagsToAdd.forEach { tag ->
                            val dbTag = ObjectMapper.MapDomainToDatabase(tag) as DatabaseTag
                            DataContext.TagDAO().Add(dbTag)
                        }
                    }.await()
                } else {
                    println("Entity with ID ${entity.Id} does not exist and cannot be updated.")
                }
            }
        }
    }
}