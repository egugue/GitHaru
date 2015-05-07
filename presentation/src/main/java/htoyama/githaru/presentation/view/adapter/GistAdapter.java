package htoyama.githaru.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.presentation.R;
import htoyama.githaru.presentation.view.widget.GistCardView;

/**
 * Adapter that manages a collectoin of {@link Gist}
 */
public class GistAdapter extends RecyclerView.Adapter<GistAdapter.ItemViewHolder> {
    private static final String TAG = GistAdapter.class.getSimpleName();

    private List<Gist> mList;
    private final LayoutInflater mInflater;
    private OnItemClickListener mListener;

    /**
     * Interface definition for a callback to be invoked when a list item is clicked.
     */
    public interface OnItemClickListener {

        /**
         * Called when a list item has been clicked.
         */
        void onItemClick(Gist gist);
    }

    public GistAdapter(Context context) {
        this(context, new ArrayList<Gist>());
    }

    public GistAdapter(Context context, List<Gist> itemList) {
        mInflater = LayoutInflater.from(context);
        mList = itemList;
    }

    /**
     * Set item list to show.
     */
    public void setItemList(List<Gist> itemList) {
        mList = itemList;
        notifyDataSetChanged();
    }

    /**
     * Set a callback to be invoked when list item is clicked.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        GistCardView view = (GistCardView)
                mInflater.inflate(R.layout.list_item_gist, viewGroup, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.bind(mList.get(i), mListener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private GistCardView mGistCardView;

        public ItemViewHolder(GistCardView view) {
            super(view);

            mGistCardView = view;
        }

        public void bind(final Gist gist,
                         @Nullable final OnItemClickListener listener) {
            mGistCardView.setGist(gist);

            if (listener == null) {
                return;
            }

            mGistCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(gist);
                }
            });
        }

    }

}
