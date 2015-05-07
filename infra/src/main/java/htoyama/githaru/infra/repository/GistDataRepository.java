package htoyama.githaru.infra.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.infra.entitiy.GistEntity;
import htoyama.githaru.infra.entitiy.mapper.GistEntityMapper;
import htoyama.githaru.infra.net.GithubApi;
import htoyama.githaru.infra.net.request.GistRequest;


/**
 * Implementation of {@link GistRepository}
 */
@Singleton
public class GistDataRepository implements GistRepository {
    //private static final String ACCESS_TOKEN = Secret.token;
    private static final String ACCESS_TOKEN = "dummy_token";
    private final GistEntityMapper mMapper;
    private final GithubApi mGithubApi;

    @Inject
    public GistDataRepository(GithubApi githubApi, GistEntityMapper mapper) {
        mGithubApi = githubApi;
        mMapper = mapper;
    }

    @Override
    public List<Gist> getList(String userName) {
        List<GistEntity> list = mGithubApi.getGistList(
                ACCESS_TOKEN, userName);

        return mMapper.map(list);
    }

    @Override
    public Gist get(String id) {
        GistEntity entity = mGithubApi.getGist(id);

        return mMapper.map(entity);
    }

    @Override
    public void create(Gist gist) {
        mGithubApi.createGist(ACCESS_TOKEN,
                GistRequest.with(gist));
    }

    @Override
    public void edit(Gist gist) {
        GistRequest request = GistRequest.with(gist);

        mGithubApi.editGist(ACCESS_TOKEN,
                gist.id,
                GistRequest.with(gist));
    }

    @Override
    public void delete(String id) {
        mGithubApi.deleteGist(ACCESS_TOKEN, id);
    }

}
