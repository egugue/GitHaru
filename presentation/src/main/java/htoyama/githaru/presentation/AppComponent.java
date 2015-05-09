package htoyama.githaru.presentation;

import javax.inject.Singleton;

import dagger.Component;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.domain.repository.RepositoryRepository;

/**
 *
 */
@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {
    //Exposed to sub-graphs.
    GistRepository gistRepository();
    RepositoryRepository repositoryRepository();
}
