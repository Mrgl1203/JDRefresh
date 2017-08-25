package refresh.gl.cpm.pullrefreshviews.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import refresh.gl.cpm.pullrefreshviews.R;

/**
 * Created by gl152 on 2017/8/24.
 */

public class JDHeader extends FrameLayout implements PtrUIHandler {

    private View jdHead;
    private ImageView ivMan;
    private ImageView ivGoods;

    /**
     * 状态识别
     */
    private int mState;

    /**
     * 重置
     * 准备刷新
     * 开始刷新
     * 结束刷新
     */
    public static final int STATE_RESET = -1;
    public static final int STATE_PREPARE = 0;
    public static final int STATE_BEGIN = 1;
    public static final int STATE_FINISH = 2;
    private AnimationDrawable anim;
    private TextView tvRefresh;
    private float currentPercent;
    public static final int MARGIN_RIGHT = 100;
    private int mOffsetToRefresh;
    private int currentpos;
    private int lastpos;

    public JDHeader(@NonNull Context context) {
        this(context, null);
    }

    public JDHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JDHeader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        jdHead = LayoutInflater.from(context).inflate(R.layout.refresh_jdhead, this, false);
        ivMan = (ImageView) jdHead.findViewById(R.id.ivMan);
        ivGoods = (ImageView) jdHead.findViewById(R.id.ivGoods);
        tvRefresh = (TextView) jdHead.findViewById(R.id.tvRefresh);
        addView(jdHead);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PREPARE;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;
        //刷新时播放跑步帧动画
        if (ivGoods.getVisibility() != GONE) {
            ivGoods.setVisibility(GONE);
            ivMan.setBackgroundResource(R.drawable.runningman);
            anim = (AnimationDrawable) ivMan.getBackground();
        }
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mState = STATE_FINISH;
        //停止动画
        ivGoods.setVisibility(VISIBLE);

        if (anim != null && anim.isRunning()) {
            anim.stop();
        }
        ivMan.setBackgroundResource(R.mipmap.a2a);
    }

    private static final String TAG = "JDHeader";

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        switch (mState) {
            case STATE_PREPARE:
                mOffsetToRefresh = frame.getOffsetToRefresh();//下拉刷新的临界值
                currentpos = ptrIndicator.getCurrentPosY(); //当前下拉的距离
                lastpos = ptrIndicator.getLastPosY(); //上次下拉的距离

                Log.i(TAG, "onUIPositionChange:----mOffsetToRefresh =" + mOffsetToRefresh + "---currentpos=" + currentpos + "---lastpos=" + lastpos);
                currentPercent = ptrIndicator.getCurrentPercent();
                ivMan.setAlpha(currentPercent);
                ivGoods.setAlpha(currentPercent);
                if (currentPercent <= 1) {
                    ivMan.setPivotX(0);
                    ivMan.setPivotY(ivMan.getHeight());
                    ivMan.setScaleX(currentPercent);
                    ivMan.setScaleY(currentPercent);
                    ivGoods.setPivotX(ivGoods.getWidth());
                    ivGoods.setPivotY(0);
                    ivGoods.setScaleX(currentPercent);
                    ivGoods.setScaleY(currentPercent);
                    FrameLayout.LayoutParams manlp = (LayoutParams) ivMan.getLayoutParams();
                    manlp.rightMargin = (int) (MARGIN_RIGHT - MARGIN_RIGHT * currentPercent);
                    ivMan.setLayoutParams(manlp);
                    Log.i(TAG, "onUIPositionChange: =" + manlp.rightMargin + "----currentPrecent=" + currentPercent);

                }
                if (currentpos >= mOffsetToRefresh) {
                    tvRefresh.setText("松开更新...");
                    ivGoods.setVisibility(GONE);
                    ivMan.setBackgroundResource(R.drawable.runningman);
                    anim = (AnimationDrawable) ivMan.getBackground();
                    if (!anim.isRunning())
                        anim.start();
                } else {
                    //停止动画
                    tvRefresh.setText("下拉更新...");
                    ivGoods.setVisibility(VISIBLE);

                    if (anim != null && anim.isRunning()) {
                        anim.stop();
                    }
                    ivMan.setBackgroundResource(R.mipmap.a2a);
                }
                break;
            case STATE_BEGIN:
                tvRefresh.setText("更新中...");
                break;
            case STATE_FINISH:
                tvRefresh.setText("更新完成");
                break;
        }
    }
}
