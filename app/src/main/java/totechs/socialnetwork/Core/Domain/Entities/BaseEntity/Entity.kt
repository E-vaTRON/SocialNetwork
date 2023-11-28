package totechs.socialnetwork.Core

import totechs.socialnetwork.Core.Application.IDomainEntity
import totechs.socialnetwork.Core.Application.IDomainEntityWithId

abstract class Entity : IDomainEntity
{
}

abstract class EntityWithId<TDbId> : Entity, IDomainEntityWithId<TDbId>
{
    abstract override var Id: TDbId

    constructor(){}

    constructor(id: TDbId)
    {
        Id = id;
    }
}