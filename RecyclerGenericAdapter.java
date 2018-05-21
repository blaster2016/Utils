import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dinesh Singh on 11/23/2017.
 *
 * @param <T> the type parameter
 */
public abstract class RecyclerGenericAdapter<T> extends RecyclerView.Adapter<RecyclerGenericAdapter.GlobalHolder> {
    private Context mContext;
    private List<T> mData = new ArrayList<>();
    private OnViewHolderClick<T> mListener;

    /**
     * Instantiates a new Global adapter.
     *
     * @param context the context
     */
    public RecyclerGenericAdapter(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Global adapter.
     *
     * @param context  the context
     * @param listener the listener
     */
    public RecyclerGenericAdapter(Context context, OnViewHolderClick<T> listener) {
        super();
        this.mContext = context;
        this.mListener = listener;
        mData = new ArrayList<>();
    }

    /**
     * Initialize item view
     *
     * @param context   the context
     * @param viewGroup the view group
     * @param viewType  the view type
     * @return the view
     */
    /*create holder */
    protected abstract View onCreateView(Context context, ViewGroup viewGroup, int viewType);

    /**
     * On View Binded to Item
     *
     * @param item       the item
     * @param position   the position
     * @param viewHolder the view holder
     */
    /*get holder once created*/
    protected abstract void onBindView(T item, int position, GlobalAdapter.GlobalHolder viewHolder);

    /**
     * Sets list.
     *
     * @param data the data
     */
    public void setList(List<T> data) {
        this.mData = data;
    }

    @Override
    public GlobalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GlobalHolder(onCreateView(mContext, parent, viewType), mListener);
    }

    @Override
    public void onBindViewHolder(GlobalAdapter.GlobalHolder holder, int position) {
        onBindView(getItem(position), position, holder);
    }

    /**
     * Gets item.
     *
     * @param index the index
     * @return the item
     */
    public T getItem(int index) {
        return ((mData != null && index < mData.size()) ? mData.get(index) : null);
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return mContext;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<T> getList() {
        return mData;
    }

    /**
     * Sets click listener.
     *
     * @param listener the listener
     */
    public void setClickListener(OnViewHolderClick listener) {
        this.mListener = listener;
    }

    /**
     * Add all.
     *
     * @param list the list
     */
    public void addAll(List<T> list) {
        if (null == list)
            list = new ArrayList<>();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * Reset.
     */
    public void reset() {
        int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
    }

    /**
     * Notify update position.
     *
     * @param mData    the m data
     * @param position the position
     */
    public void notifyUpdatePosition(List<T> mData, int position) {
        this.mData = mData;
        notifyItemChanged(position);
    }

    /**
     * Notify delete position.
     *
     * @param mData    the m data
     * @param position the position
     */
    public void notifyDeletePosition(List<T> mData, int position) {
        this.mData = mData;
        notifyItemRemoved(position);
        notifyItemChanged(position);
    }

    /**
     * Notify add position.
     *
     * @param mData    the m data
     * @param position the position
     */
    public void notifyAddPosition(List<T> mData, int position) {
        this.mData = mData;
        notifyItemInserted(position);
        notifyItemChanged(position);
    }

    /**
     * Update item changed.
     *
     * @param position the position
     */
    public void updateItemChanged(int position) {
        notifyItemChanged(position);
    }

    /**
     * The interface On view holder click.
     *
     * @param <T> the type parameter
     */
    public interface OnViewHolderClick<T> {
        /**
         * On item click listner.
         *
         * @param view     the view
         * @param position the position
         * @param item     the item
         */
        void onItemClickListner(View view, int position, T item);
    }

    /**
     * The type Global holder.
     */
    /*Holder class init*/
    class GlobalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * The Views.
         */
        HashMap<Integer, View> views;

        /**
         * Instantiates a new Global holder.
         *
         * @param itemView  the item view
         * @param mListener the m listener
         */
        public GlobalHolder(View itemView, OnViewHolderClick mListener) {
            super(itemView);
            views = new HashMap<>();
            views.put(0, itemView);
            if (mListener != null)
                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null)
                mListener.onItemClickListner(v, getLayoutPosition(), mData.get(getLayoutPosition()));
        }

        /*find view by id and store in map*/
        private void initViewById(int id) {
            View view = (getView() != null ? getView().findViewById(id) : null);

            if (view != null)
                views.put(id, view);
        }

        /*send parent view*/
        private View getView() {
            return getView(0);
        }

        /**
         * Gets view.
         *
         * @param id the id
         * @return the view
         */
        /*get views from layout first check in map if found sends, else findviewbyid is done*/
        public View getView(int id) {
            if (views.containsKey(id))
                return views.get(id);
            else
                initViewById(id);

            return views.get(id);
        }
    }

}

