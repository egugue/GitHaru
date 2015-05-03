package htoyama.githaru.presentation;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import htoyama.githaru.infra.InfraModule;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
@Singleton //TODO: should move component provided only singleton instance
@Component(
        modules = {
                InfraModule.class
        }
)
public interface AppComponent {
    void inject(TopActivity a);
}
