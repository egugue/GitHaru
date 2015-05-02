package htoyama.githaru.infra.net;

import java.util.List;

import htoyama.githaru.infra.entitiy.RepositoryEntity;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 *
 */
public interface GithubApi {

    @GET("/users/{user}/repos")
    List<RepositoryEntity> getRepositoryList(
            @Path("user") String user
    );

}
