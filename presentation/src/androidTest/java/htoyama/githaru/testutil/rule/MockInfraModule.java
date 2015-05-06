package htoyama.githaru.testutil.rule;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.domain.repository.RepositoryRepository;

@Module
public class MockInfraModule {

    @Provides
    public RepositoryRepository provideRepositoryRepository() {
        return Mockito.mock(RepositoryRepository.class);
    }

    @Provides
    @Singleton
    public GistRepository provideGistRepository() {
        return Mockito.mock(GistRepository.class);
    }
}
