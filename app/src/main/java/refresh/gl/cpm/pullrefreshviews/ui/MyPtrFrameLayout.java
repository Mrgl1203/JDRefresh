package refresh.gl.cpm.pullrefreshviews.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by gl152 on 2017/8/23.
 */

public class MyPtrFrameLayout extends PtrFrameLayout implements AbsListView.OnScrollListener {
    MTHeader mtHeader;

    public MyPtrFrameLayout(Context context) {
        this(context, null);
    }

    public MyPtrFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mtHeader = new MTHeader(context);
        setHeaderView(mtHeader);
        addPtrUIHandler(mtHeader);
    }
    public void changeJDHeader(JDHeader head){
        setHeaderView(head);
        addPtrUIHandler(head);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
