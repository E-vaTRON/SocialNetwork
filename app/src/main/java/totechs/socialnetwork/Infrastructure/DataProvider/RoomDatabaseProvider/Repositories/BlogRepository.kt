package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import totechs.socialnetwork.Core.Application.IBlogRepository
import totechs.socialnetwork.Core.Application.IObjectMapper
import totechs.socialnetwork.Core.Application.Services.IDAOMapper
import totechs.socialnetwork.Core.Blog
import java.util.UUID
import kotlin.coroutines.CoroutineContext

class BlogRepository : IBlogRepository
{
    var DataContext : SqliteDataConext
    var ObjectMapper : IObjectMapper

    constructor(conext: SqliteDataConext,
                objectMapper : IObjectMapper)
    {
        DataContext = conext
        ObjectMapper = objectMapper
    }

    override suspend fun FindAllAsync(predicate: ((Blog) -> Boolean)?,coroutineContext: CoroutineContext)
        : List<Blog>
    {
        TODO("Not yet implemented")
    }

    override suspend fun FindByIdAsync(id: String, coroutineContext: CoroutineContext)
        : Blog?
    {
        TODO("Not yet implemented")
    }

    override suspend fun AddAsync(entity: Blog, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }

    override suspend fun AddRangeAsync(entities: List<Blog>, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }

    override suspend fun AddRangeAsync(vararg entities: Blog, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }

    override suspend fun UpdateAsync(entity: Blog, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteAsync(entity: Blog, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteByIdAsync(id: String, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteRangeAsync(entities: List<Blog>, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteRangeAsync( vararg entities: Blog, coroutineContext: CoroutineContext)
    {
        TODO("Not yet implemented")
    }


//    override suspend fun FindAllAsync(predicate: ((DomainBlog) -> Boolean)?,
//                                      coroutineContext: CoroutineContext): List<DomainBlog>
//    = coroutineScope {
//        mapper = MapToDomain.build()
//
//        val blogs = async(Dispatchers.IO) { DataContext.BlogDAO().FindAll() }.await()
//
//        val result = if (predicate != null) {
//            blogs.filter { predicate.invoke(mapper.map(it)) }
//        } else { blogs }
//
//        mapper.map(result)
//    }

//    override suspend fun AddAsync(entity: DomainBlog, coroutineContext: CoroutineContext)
//    {
//        coroutineScope {
//            launch(coroutineContext) {
//                val dbEntity = ObjectMapper.MapDomainToDatabase(entity) as DatabaseBlog
//                val check = async(Dispatchers.IO)
//                {
//                    Dao.Add(dbEntity)
//                }.await()
//                if (check != null)
//                {
//                    val hello = "added error"
//                }
//            }
//        }
//    }


}