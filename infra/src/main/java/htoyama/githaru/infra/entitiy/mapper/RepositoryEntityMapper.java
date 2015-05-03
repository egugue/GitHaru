package htoyama.githaru.infra.entitiy.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import htoyama.githaru.domain.entity.Repository;
import htoyama.githaru.infra.entitiy.RepositoryEntity;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
@Singleton
public class RepositoryEntityMapper {
    private OwnerEntityMapper mOwnerEntityMapper;

    @Inject
    public RepositoryEntityMapper(OwnerEntityMapper ownerEntityMapper) {
        mOwnerEntityMapper = ownerEntityMapper;
    }

    public Repository map(RepositoryEntity entity) {
        //TODO: null
        return new Repository(entity.name,
                mOwnerEntityMapper.map(entity.owner));
    }

    public List<Repository> map(List<RepositoryEntity> entityList) {
        List<Repository> list = new ArrayList<>();

        Repository repository;
        for (RepositoryEntity entity : entityList) {
            repository = map(entity);
            list.add(repository);
        }

        return list;
    }

}
