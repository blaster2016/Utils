
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * The type Keyboard state watcher.
 */
public class KeyboardWatcher implements ViewTreeObserver.OnGlobalLayoutListener {

    private SoftKeyboardStateListener mListner;
    private final View activityRootView;
    private boolean isSoftKeyboardOpened;

    /**
     * Instantiates a new Keyboard state watcher.
     *
     * @param activityRootView the activity root view
     */
    public KeyboardWatcher(View activityRootView) {
        this(activityRootView, false);
    }

    /**
     * Instantiates a new Keyboard state watcher.
     *
     * @param activityRootView     the activity root view
     * @param isSoftKeyboardOpened the is soft keyboard opened
     */
    public KeyboardWatcher(View activityRootView, boolean isSoftKeyboardOpened) {
        this.activityRootView = activityRootView;
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
        if(null!=activityRootView)
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        final Rect r = new Rect();
        activityRootView.getWindowVisibleDisplayFrame(r);
        final int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
        if (heightDiff > activityRootView.getRootView().getHeight() / 4) { // if more than 100 pixels, its probably a keyboard...
            isSoftKeyboardOpened = true;
            notifyOnSoftKeyboardOpened();
        } else {
            isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }
    }

    /**
     * Is soft keyboard opened boolean.
     *
     * @return the boolean
     */
    public boolean isSoftKeyboardOpened() {
        return isSoftKeyboardOpened;
    }

    /**
     * Add soft keyboard state listener.
     *
     * @param listener the listener
     */
    public void addSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        mListner = listener;
    }

    /**
     * Remove soft keyboard state listener.
     *
     * @param listener the listener
     */
    public void removeSoftKeyboardStateListener() {
        mListner = null;
    }

    private void notifyOnSoftKeyboardOpened() {
        if (mListner != null)
            mListner.onSoftKeyboardOpened();
    }

    private void notifyOnSoftKeyboardClosed() {
        if (mListner != null)
            mListner.onSoftKeyboardClosed();
    }

    /**
     * The interface Soft keyboard state listener.
     */
    public interface SoftKeyboardStateListener {
        /**
         * On soft keyboard opened.
         */
        void onSoftKeyboardOpened();

        /**
         * On soft keyboard closed.
         */
        void onSoftKeyboardClosed();
    }

}