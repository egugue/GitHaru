package htoyama.githaru.domain.repository;

import java.util.List;

import htoyama.githaru.domain.entity.Gist;

/**
 * The Repository that gets {@link Gist} related data.
 */
public interface GistRepository {

    /**
     * Get List of {@link Gist}.
     *
     * @param userName The user's name to retrive his Gist.
     */
    List<Gist> getList(String userName);

    /**
     * Get a {@link Gist}.
     *
     * @param id The id to retrive Gist.
     */
    Gist get(String id);

    /**
     * Create a {@link Gist}.
     *
     * @param gist The Gist to create.
     */
    void create(Gist gist);

    /**
     * Edit a {@link Gist}.
     *
     * @param gist The Gist to modify
     */
    void edit(Gist gist);

    /**
     * Delete a {@link Gist}.
     *
     * @param id The id to delete Gist.
     */
    void delete(String id);

}
