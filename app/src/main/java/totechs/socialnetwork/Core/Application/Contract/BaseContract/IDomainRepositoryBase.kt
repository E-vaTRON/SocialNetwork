package totechs.socialnetwork.Core.Application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import totechs.socialnetwork.Core.Application.IDomainEntity
import kotlin.coroutines.CoroutineContext

interface IDomainRepositoryBase <TDomainEntity>
        where TDomainEntity : IDomainEntity
{
    suspend fun FindAllAsync( predicate: ((TDomainEntity) -> Boolean)? = null, coroutineContext: CoroutineContext = Dispatchers.Default): Flow<List<TDomainEntity>>

    suspend fun FindByIdAsync(id: String, coroutineContext: CoroutineContext = Dispatchers.Default, isQuickFind: Boolean = true): TDomainEntity?

    suspend fun AddAsync(entity: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun AddRangeAsync(entities: List<TDomainEntity>, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun AddRangeAsync(coroutineContext: CoroutineContext = Dispatchers.Default, vararg entities: TDomainEntity)

    suspend fun UpdateAsync(entity: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteAsync(entity: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteByIdAsync(id: String, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteRangeAsync(entities: List<TDomainEntity>, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteRangeAsync(coroutineContext: CoroutineContext = Dispatchers.Default, vararg entities: TDomainEntity)
}