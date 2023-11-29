package totechs.socialnetwork.Infrastructure.DataProvider.RoomDatabaseProvider.Repositories

import kotlinx.coroutines.flow.Flow
import totechs.socialnetwork.Core.Application.ITagRepository
import totechs.socialnetwork.Core.Tag
import kotlin.coroutines.CoroutineContext

class TagRepository: ITagRepository
{
    override suspend fun FindAllAsync(
        predicate: ((Tag) -> Boolean)?,
        coroutineContext: CoroutineContext
    ): List<Tag> {
        TODO("Not yet implemented")
    }

    override suspend fun FindByIdAsync(
        id: String,
        coroutineContext: CoroutineContext,
    ): Tag? {
        TODO("Not yet implemented")
    }

    override suspend fun AddAsync(entity: Tag, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun AddRangeAsync(entities: List<Tag>, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun AddRangeAsync(vararg entities: Tag, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun UpdateAsync(entity: Tag, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteAsync(entity: Tag, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteByIdAsync(id: String, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteRangeAsync(entities: List<Tag>, coroutineContext: CoroutineContext) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteRangeAsync(
        vararg entities: Tag,
        coroutineContext: CoroutineContext
    ) {
        TODO("Not yet implemented")
    }
}