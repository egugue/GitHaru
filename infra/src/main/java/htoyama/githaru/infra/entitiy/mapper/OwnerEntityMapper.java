package htoyama.githaru.infra.entitiy.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import htoyama.githaru.domain.entity.Owner;
import htoyama.githaru.infra.entitiy.OwnerEntity;

/**
 * The Dxo to map {@link OwnerEntity} to {@link Owner}.
 */
@Singleton
public class OwnerEntityMapper {

    @Inject
    public OwnerEntityMapper() {
    }

    /**
     * Map {@link OwnerEntity} to {@link Owner}.
     */
    public Owner map(OwnerEntity entity) {
        Owner owner = new Owner(entity.name);
        owner.id = entity.id;
        return owner;
    }
}
