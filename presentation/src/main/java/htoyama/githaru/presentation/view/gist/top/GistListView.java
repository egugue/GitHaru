package htoyama.githaru.presentation.view.gist.top;

import java.util.List;

import htoyama.githaru.domain.entity.Gist;

/**
* Interface for show List of {@link Gist}.
*/
interface GistListView {

    /**
     * Show pregress view while retriving data.
     */
    void showLoading();

    /**
     * Hide progress view when retriving data.
     */
    void hideLoading();

    /**
     * Show List of {@link Gist}
     */
    void showGistList(List<Gist> list);

    /**
     * Show additinal {@link Gist}.
     *
     * @param gist The {@link Gist} added to.
     *      This gist is used to animate slide in list view.
     */
    void showAdditionalGist(Gist gist);

    /**
     * Show error when fail to retriving data.
     *
     * @param message The message to show.
     */
    void showError(String message);

}
