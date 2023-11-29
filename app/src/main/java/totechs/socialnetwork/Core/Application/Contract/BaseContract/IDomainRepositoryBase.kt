package totechs.socialnetwork.Core.Application

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface IDomainRepositoryBase <TDomainEntity>
        where TDomainEntity : IDomainEntity
{
    suspend fun FindAllAsync( predicate: ((TDomainEntity) -> Boolean)? = null, coroutineContext: CoroutineContext = Dispatchers.Default): List<TDomainEntity>

    suspend fun FindByIdAsync(id: String, coroutineContext: CoroutineContext = Dispatchers.Default): TDomainEntity?

    suspend fun AddAsync(entity: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun AddRangeAsync(entities: List<TDomainEntity>, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun AddRangeAsync(vararg entities: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun UpdateAsync(entity: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteAsync(entity: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteByIdAsync(id: String, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteRangeAsync(entities: List<TDomainEntity>, coroutineContext: CoroutineContext = Dispatchers.Default)

    suspend fun DeleteRangeAsync(vararg entities: TDomainEntity, coroutineContext: CoroutineContext = Dispatchers.Default)
}