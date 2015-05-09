package htoyama.githaru.infra;


import dagger.Module;
import dagger.Provides;
import htoyama.githaru.infra.net.ApiErrorHandler;
import htoyama.githaru.infra.net.GithubApi;
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

}
