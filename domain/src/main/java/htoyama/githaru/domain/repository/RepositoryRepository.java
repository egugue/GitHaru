package htoyama.githaru.domain.repository;

import java.util.List;

import htoyama.githaru.domain.entity.Repository;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public interface RepositoryRepository {

    List<Repository> getList(String userName);

    void create(Repository repository);

    Repository get(String userName, String reposistoryName);

    void edit(Repository repository);

    void delete(Repository repository);

}
