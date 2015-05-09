package htoyama.githaru.presentation.view.gist;

import dagger.Component;
import htoyama.githaru.presentation.AppComponent;
import htoyama.githaru.presentation.view.common.di.AbstractActivityComponent;
import htoyama.githaru.presentation.view.common.di.ActivityModule;
import htoyama.githaru.presentation.view.common.di.PerActivity;

/**
 * A {@link Component} related gist.
 */
@Component(
        dependencies = AppComponent.class,
        modules = ActivityModule.class
)
@PerActivity
public interface GistComponent extends AbstractActivityComponent {
    void inject(TopActivity topActivity);
    void inject(GistEditActivity activity);
}
