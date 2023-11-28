package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider

import dev.krud.shapeshift.ShapeShift
import dev.krud.shapeshift.ShapeShiftBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import totechs.socialnetwork.Core.Application.IBlogRepository
typealias DomainBlog = totechs.socialnetwork.Core.Blog
typealias DatabaseBlog = Blog

class BlogRepository : IBlogRepository<DomainBlog>
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

    override fun Insert(blog: DomainBlog)
    {
        mapper = MapToDatabase.build()
        DataContext.BlogDAO().Insert(mapper.map<DomainBlog, DatabaseBlog>(blog))
    }

    override fun Delete(blog: DomainBlog)
    {
        mapper = MapToDatabase.build()
        DataContext.BlogDAO().Delete(mapper.map<DomainBlog, DatabaseBlog>(blog))
    }

    override fun GetAll(): Flow<List<DomainBlog>>
    {
        mapper = MapToDomain.build()
        return DataContext.BlogDAO().GetAll().map()
        { blogs ->
            blogs.map()
            {
                mapper.map<DatabaseBlog, DomainBlog>(it)
            }
        }
    }

    override fun GetById(Id: String): DomainBlog?
    {
        mapper = MapToDomain.build()
        return DataContext.BlogDAO().GetById(Id)?.let()
               { mapper.map<DatabaseBlog, DomainBlog>(it) }
    }
}