package htoyama.githaru.presentation.view.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.presentation.R;

/**
 * Custom {@link CardView} class for showing Gist data.
 */
public class GistCardView extends CardView {
    private TextView mTitleTextView;
    private TextView mBodyTextView;
    private TextView mCreatedTextView;

    public GistCardView(Context context) {
        this(context, null);
    }

    public GistCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Get title.
     */
    public CharSequence getTitle() {
        return mTitleTextView.getText();
    }

    private void setTitle(Gist gist) {
        if (!TextUtils.isEmpty(gist.title)) {
            mTitleTextView.setText(gist.title);
            return;
        }

        String title = "gist:" + gist.id;
        mTitleTextView.setText(title);
    }

    public void setGist(Gist gist) {
        setTitle(gist);

        //TODO
        String body = "";
        for (File file : gist.fileList) {
            body += file.name + ", ";
        }

        mBodyTextView.setText(body);
    }

    private void init() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.view_gist_card, this, true);

        mTitleTextView = (TextView) findViewById(R.id.gcv___title);
        mCreatedTextView = (TextView) findViewById(R.id.gcv__created);
        mBodyTextView = (TextView) findViewById(R.id.gcv___body);
    }

}
