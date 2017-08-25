# JDRefresh
## 防京东下拉刷新效果
* 基于强大的下拉刷新框架Ultra Pull To Refresh，自定义头部完成京东下拉刷新<br>
 ```
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
   ```
   ![img](https://github.com/Mrgl1203/JDRefresh/blob/master/app/jdrefresh-gif.gif)
    
