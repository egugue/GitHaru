package htoyama.githaru.presentation.internal.di;

import dagger.Component;
import htoyama.githaru.presentation.AppComponent;
import htoyama.githaru.presentation.view.activity.GistEditActivity;
import htoyama.githaru.presentation.view.activity.TopActivity;

/**
 * A {@link Component} related gist.
 */
/*
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
    void inject(GistEditActivity activity);
}
