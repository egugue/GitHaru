package htoyama.githaru.infra;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.domain.repository.RepositoryRepository;
import htoyama.githaru.infra.entitiy.mapper.RepositoryEntityMapper;
import htoyama.githaru.infra.net.ApiErrorHandler;
import htoyama.githaru.infra.net.GithubApi;
import htoyama.githaru.infra.repository.GistDataRepository;
import htoyama.githaru.infra.repository.RepositoryDataRepository;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 *
 */
@Module
public class InfraModule {

    @Provides
    public RestAdapter provideRestAdapter() {
        //TODO: check if need provideEndpoint
        return new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new ApiErrorHandler())
                .setClient(new OkClient())
                .build();
    }

    @Provides
    public GithubApi provideGithubApi(RestAdapter restAdapter) {
        return restAdapter.create(GithubApi.class);
    }

    @Provides
    public RepositoryRepository provideRepositoryRepository(
            GithubApi githubApi,
            RepositoryEntityMapper mapper) {

       return new RepositoryDataRepository(githubApi, mapper) ;
    }

    @Provides
    @Singleton
    public GistRepository provideGistRepository(GistDataRepository repository) {
        return repository;
    }

}
