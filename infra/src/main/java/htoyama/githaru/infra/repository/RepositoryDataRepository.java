package htoyama.githaru.infra.repository;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Repository;
import htoyama.githaru.domain.repository.RepositoryRepository;
import htoyama.githaru.infra.Secret;
import htoyama.githaru.infra.entitiy.RepositoryEntity;
import htoyama.githaru.infra.entitiy.mapper.RepositoryEntityMapper;
import htoyama.githaru.infra.exception.RetrofitApiException;
import htoyama.githaru.infra.net.GithubApi;
import retrofit.RetrofitError;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public class RepositoryDataRepository implements RepositoryRepository {
    private static final String TAG = RepositoryDataRepository.class.getSimpleName();

    private final GithubApi mGithubApi;
    private final RepositoryEntityMapper mMapper;

    @Inject
    public RepositoryDataRepository(GithubApi githubApi,
                                    RepositoryEntityMapper mapper) {
        mGithubApi = githubApi;
        mMapper = mapper;
    }

    @Override
    public List<Repository> getList(String userName) {

        try {
            List<RepositoryEntity> list = mGithubApi.getRepositoryList(userName);
            //List<RepositoryEntity> list = mGithubApi.test();
            return mMapper.map(list);

        } catch (RetrofitError e) {
            Log.d("---------", e.getClass().getName());
            throw new RetrofitApiException(e);
        }

    }

    @Override
    public void create(Repository repository) {

    }

    @Override
    public Repository get(String userName, String reposistoryName) {
        return null;
    }

    @Override
    public void edit(Repository repository) {

    }

    @Override
    public void delete(Repository repository) {
        mGithubApi.deleteRepository(Secret.token,
                repository.owner.name,
                repository.name
                );
    }


}
