package htoyama.githaru.presentation.internal.di;

import dagger.Component;
import htoyama.githaru.presentation.AppComponent;
import htoyama.githaru.presentation.TopActivity;
import htoyama.githaru.presentation.internal.di.AbstractActivityComponent;
import htoyama.githaru.presentation.internal.di.ActivityModule;
import htoyama.githaru.presentation.internal.di.PerActivity;

/**
 * A {@link Component} related gist.
 */
@Component(
        dependencies = AppComponent.class,
        modules = {
                ActivityModule.class,
        }
)
@PerActivity
public interface GistComponent extends AbstractActivityComponent {
    void inject(TopActivity topActivity);
}