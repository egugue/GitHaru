package htoyama.githaru.presentation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.domain.repository.RepositoryRepository;
import htoyama.githaru.infra.InfraModule;
import htoyama.githaru.infra.repository.GistDataRepository;
import htoyama.githaru.infra.repository.RepositoryDataRepository;

/**
 * {@link Module} that manages object lived during applicatoin lifecycle.
 */
@Module(
        includes = InfraModule.class
)
public class AppModule {

    @Provides
    public RepositoryRepository provideRepositoryRepository(
            RepositoryDataRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    public GistRepository provideGistRepository(GistDataRepository repository) {
        return repository;
    }

}
