package htoyama.githaru.presentation;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.domain.repository.RepositoryRepository;
import htoyama.githaru.infra.InfraModule;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
@Singleton
@Component(
        modules = {
                InfraModule.class
        }
)
public interface AppComponent {
    GistRepository gistRepository();
    RepositoryRepository repositoryRepository();
}
