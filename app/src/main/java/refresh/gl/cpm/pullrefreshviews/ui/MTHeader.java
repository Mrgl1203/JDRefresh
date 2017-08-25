package refresh.gl.cpm.pullrefreshviews.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import refresh.gl.cpm.pullrefreshviews.R;

/**
 * Created by gl152 on 2017/8/23.
 */

public class MTHeader extends FrameLayout implements PtrUIHandler {


    private View headView;
    private TextView tvRefresh;

    public MTHeader(@NonNull Context context) {
        this(context, null);
    }

    public MTHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTHeader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        headView = LayoutInflater.from(context).inflate(R.layout.refresh_head1, this);
        tvRefresh = (TextView) headView.findViewById(R.id.tvRefresh);

    }

    @Override//初始化状态
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override//开始向下拉的时候调用
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        tvRefresh.setText("下拉刷新");
    }

    @Override//刷新过程时调用
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        tvRefresh.setText("正在加载");
    }

    @Override //刷新完成后调用,向上移动时调用
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        tvRefresh.setText("加载完成");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
