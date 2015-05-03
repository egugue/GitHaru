package htoyama.githaru.infra.net;

import java.util.List;

import htoyama.githaru.infra.entitiy.GistEntity;
import htoyama.githaru.infra.entitiy.RepositoryEntity;
import htoyama.githaru.infra.net.request.GistRequest;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Retriving github data from the network.
 * <p>
 * All methods in the class throw {@link retrofit.RetrofitError} when caused Some error.
 *
 * @see <a href="https://developer.github.com/v3/">Api Document</a>
 */
public interface GithubApi {

    /**
     * Create a gist.
     *
     * @param authorization The OAuth2 Token.
     * @param gistRequest The request body.
     */
    @POST("/gists")
    Response createGist(
            @Header("Authorization") String authorization,
            @Body GistRequest gistRequest
    );

    /**
     * Edit a gist.
     *
     * @param authorization The OAuth2 Token.
     * @param id The Gist id.
     * @param gistRequest The request body.
     */
    @PATCH("/gists/{id}")
    Response editGist(
            @Header("Authorization") String authorization,
            @Path("id") String id,
            @Body GistRequest gistRequest
    );

    /**
     * Delete a gist.
     *
     * @param authorization The OAuth2 Token.
     * @param id The gist id.
     */
    @DELETE("/gists/{id}")
    Response deleteGist(
            @Header("Authorization") String authorization,
            @Path("id") String id
    );

    /**
     * Get a gist.
     *
     * @param id The gist id.
     */
    @GET("/gists/{id}")
    GistEntity getGist(
            @Path("id") String id
    );

    /**
     * Get a list of gist.
     *
     * @param authorization The OAuth2 Token.
     * @param user The user name.
     */
    @GET("/users/{user}/gists")
    List<GistEntity> getGistList(
            @Header("Authorization") String authorization,
            @Path("user") String user
    );

    @GET("/users/{user}/repos")
    List<RepositoryEntity> getRepositoryList(
            @Path("user") String user
    );

    @DELETE("/repos/{owner}/{repo}")
    Response deleteRepository(
            @Header("Authorization") String authorization,
            @Path("owner") String owner,
            @Path("repo") String repository
    );

    @GET("/zukan/search")
    List<RepositoryEntity> test();

}
