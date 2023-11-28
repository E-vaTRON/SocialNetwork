package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import dev.krud.shapeshift.ShapeShift
import dev.krud.shapeshift.ShapeShiftBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import totechs.socialnetwork.Core.Application.IBlogRepository
import kotlin.coroutines.CoroutineContext

typealias DomainBlog = totechs.socialnetwork.Core.Blog
typealias DatabaseBlog = Blog

class BlogRepository : IBlogRepository
{
    lateinit var mapperBuilder : ShapeShiftBuilder
    lateinit var mapper : ShapeShift
    lateinit var DataContext : SqliteDataConext

    val MapToDatabase = mapperBuilder.withMapping<DomainBlog, DatabaseBlog>
    {
        DomainBlog::Id mappedTo DatabaseBlog::Id
        DomainBlog::Deleted mappedTo DatabaseBlog::Deleted
        DomainBlog::CreatedOn mappedTo DatabaseBlog::CreatedOn
        DomainBlog::LastUpdatedOn mappedTo DatabaseBlog::LastUpdatedOn
        DomainBlog::Title mappedTo DatabaseBlog::Title
        DomainBlog::Description mappedTo DatabaseBlog::Description
        DomainBlog::ImageUrl mappedTo DatabaseBlog::ImageUrl
        DomainBlog::Deleted mappedTo DatabaseBlog::Deleted
    }

    val MapToDomain = mapperBuilder.withMapping<DatabaseBlog, DomainBlog>
    {
        DatabaseBlog::Id mappedTo DomainBlog::Id
        DatabaseBlog::Deleted mappedTo DomainBlog::Deleted
        DatabaseBlog::CreatedOn mappedTo DomainBlog::CreatedOn
        DatabaseBlog::LastUpdatedOn mappedTo DomainBlog::LastUpdatedOn
        DatabaseBlog::Title mappedTo DomainBlog::Title
        DatabaseBlog::Description mappedTo DomainBlog::Description
        DatabaseBlog::ImageUrl mappedTo DomainBlog::ImageUrl
        DatabaseBlog::Deleted mappedTo DomainBlog::Deleted
    }

//    override fun Insert(blog: DomainBlog)
//    {
//        mapper = MapToDatabase.build()
//        DataContext.BlogDAO().Insert(mapper.map<DomainBlog, DatabaseBlog>(blog))
//    }
//
//    override fun Delete(blog: DomainBlog)
//    {
//        mapper = MapToDatabase.build()
//        DataContext.BlogDAO().Delete(mapper.map<DomainBlog, DatabaseBlog>(blog))
//    }
//
//    override fun GetAll(): Flow<List<DomainBlog>>
//    {
//        mapper = MapToDomain.build()
//        return DataContext.BlogDAO().GetAll().map()
//        { blogs ->
//            blogs.map()
//            {
//                mapper.map<DatabaseBlog, DomainBlog>(it)
//            }
//        }
//    }
//
//    override fun GetById(Id: String): DomainBlog?
//    {
//        mapper = MapToDomain.build()
//        return DataContext.BlogDAO().GetById(Id)?.let()
//               { mapper.map<DatabaseBlog, DomainBlog>(it) }
//    }

    override suspend fun FindAllAsync(
        predicate: ((totechs.socialnetwork.Core.Blog) -> Boolean)?,
        coroutineContext: CoroutineContext
    ): Flow<List<totechs.socialnetwork.Core.Blog>> {
        TODO("Not yet implemented")
    }

    override suspend fun FindByIdAsync(
        id: String,
        coroutineContext: CoroutineContext,
        isQuickFind: Boolean
    ): totechs.socialnetwork.Core.Blog? {
        TODO("Not yet implemented")
    }

    override suspend fun AddAsync(
        entity: totechs.socialnetwork.Core.Blog,
        coroutineContext: CoroutineContext
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun AddRangeAsync(
        entities: List<totechs.socialnetwork.Core.Blog>,
        coroutineContext: CoroutineContext
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun AddRangeAsync(
        coroutineContext: CoroutineContext,
        vararg entities: totechs.socialnetwork.Core.Blog
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun UpdateAsync(
        entity: totechs.socialnetwork.Core.Blog,
        coroutineContext: CoroutineContext
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteAsync(
        entity: totechs.socialnetwork.Core.Blog,
        coroutineContext: CoroutineContext
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteByIdAsync(id: String, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteRangeAsync(
        entities: List<totechs.socialnetwork.Core.Blog>,
        coroutineContext: CoroutineContext
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteRangeAsync(
        coroutineContext: CoroutineContext,
        vararg entities: totechs.socialnetwork.Core.Blog
    ) {
        TODO("Not yet implemented")
    }
}