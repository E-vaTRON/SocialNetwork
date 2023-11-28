package totechs.socialnetwork.Infrastructure

import totechs.socialnetwork.Core.Application.IDatabaseEntity
import totechs.socialnetwork.Core.Application.IDatabaseEntityWithId

abstract class Entity : IDatabaseEntity
{
}

abstract class EntityWithId<TId> : Entity, IDatabaseEntityWithId<TId>
{
    abstract override var Id: TId

    constructor(){}

    constructor(id: TId)
    {
        Id = id;
    }
}