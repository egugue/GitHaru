package htoyama.githaru.presentation.view.common;


import rx.Subscription;

/**
 * Base Presenter.
 * A Presenter is controled by the View ({@link android.app.Activity} or {@link android.app.Fragment}).
 * The views control a subclass of {@link Presenter} with their lifecycle.
 */
public interface Presenter extends Subscription {
    /**
     * This method must be called in the view's onResume().
     */
    void resume();

    /**
     * This method must be called in the view's onPause().
     */
    void pause();

    /**
     * This method must be called in the view's onDestroy().
     */
    void destroy();
}
