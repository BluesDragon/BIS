package com.yangmiao.bis.widget.expandable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.yangmiao.bis.widget.expandable.model.GroupItem;


/**
 * 可扩展的固定Header的列表
 */
public class StickyHeaderExpandableListView extends ExpandableListView
        implements OnScrollListener {

    public interface OnHeaderUpdateListener {
        public View getHeaderView(int groupPosition);

        public void updateHeader(View headerView, int groupPosition);

        public boolean onHeaderClick(int groupPosition, int x, int y);
    }

    private View mHeaderView;
    private View mTouchTarget;

    private int mHeaderWidth;
    private int mHeaderHeight;

    private OnScrollListener mScrollListener;
    private OnHeaderUpdateListener mHeaderUpdateListener;

    private boolean mActionDownHappened = false;

    public StickyHeaderExpandableListView(Context context) {
        super(context);
        initView();
    }

    public StickyHeaderExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StickyHeaderExpandableListView(Context context, AttributeSet attrs,
                                          int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setFadingEdgeLength(0);
        setOnScrollListener(this);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        if (l != this) {
            mScrollListener = l;
        } else {
            mScrollListener = null;
        }
        super.setOnScrollListener(this);
    }

    /**
     * 给group添加点击事件监听
     */
    public void setOnGroupClickListener(
            OnGroupClickListener onGroupClickListener) {
        super.setOnGroupClickListener(onGroupClickListener);
    }

    public void setAdapter(ExpandableListAdapter adapter, OnHeaderUpdateListener listener) {
        super.setAdapter(adapter);
        if (mHeaderUpdateListener == null) {
            setOnHeaderUpdateListener(listener);
        }
    }

    public void setOnHeaderUpdateListener(OnHeaderUpdateListener listener) {
        mHeaderUpdateListener = listener;
        if (listener == null) {
            mHeaderView = null;
            mHeaderWidth = mHeaderHeight = 0;
            return;
        }
        int firstVisiblePos = getFirstVisiblePosition();
        int firstVisibleGroupPos = getPackedPositionGroup(getExpandableListPosition(firstVisiblePos));
        mHeaderView = listener.getHeaderView(firstVisibleGroupPos);
        if (mHeaderView != null && mHeaderView.getLayoutParams() == null) {
            mHeaderView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
        listener.updateHeader(mHeaderView, firstVisibleGroupPos);
        requestLayout();
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeaderView == null) {
            return;
        }
        measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
        mHeaderWidth = mHeaderView.getMeasuredWidth();
        mHeaderHeight = mHeaderView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mHeaderView == null) {
            return;
        }
        int delta = mHeaderView.getTop();
        mHeaderView.layout(0, delta, mHeaderWidth, mHeaderHeight + delta);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mHeaderView != null) {
            try {
                drawChild(canvas, mHeaderView, getDrawingTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int pos = pointToPosition(x, y);
        int groupPosition = getPackedPositionGroup(getExpandableListPosition(pos));
        if (mHeaderView != null && y >= mHeaderView.getTop()
                && y <= mHeaderView.getBottom()) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                mTouchTarget = getTouchTarget(mHeaderView, x, y);
                mActionDownHappened = true;
            } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                View touchTarget = getTouchTarget(mHeaderView, x, y);
                if (touchTarget == mTouchTarget && mTouchTarget.isClickable()) {
                    mTouchTarget.performClick();
                    invalidate(new Rect(0, 0, mHeaderWidth, mHeaderHeight));
                } else if (mHeaderUpdateListener == null
                        || mHeaderUpdateListener.onHeaderClick(groupPosition,
                        x, y)) {
                    if (groupPosition != INVALID_POSITION
                            && mActionDownHappened) {
                        if (isGroupExpanded(groupPosition)) {
                            collapseGroup(groupPosition);
                        } else {
                            expandGroup(groupPosition);
                        }
                    }
                }
                mActionDownHappened = false;
            }
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    private View getTouchTarget(View view, int x, int y) {
        if (!(view instanceof ViewGroup)) {
            return view;
        }
        ViewGroup parent = (ViewGroup) view;
        int childrenCount = parent.getChildCount();
        final boolean customOrder = isChildrenDrawingOrderEnabled();
        View target = null;
        for (int i = childrenCount - 1; i >= 0; i--) {
            final int childIndex = customOrder ? getChildDrawingOrder(
                    childrenCount, i) : i;
            final View child = parent.getChildAt(childIndex);
            if (isTouchPointInView(child, x, y)) {
                target = child;
                break;
            }
        }
        if (target == null) {
            target = parent;
        }

        return target;
    }

    private boolean isTouchPointInView(View view, int x, int y) {
        if (view.isClickable() && y >= view.getTop() && y <= view.getBottom()
                && x >= view.getLeft() && x <= view.getRight()) {
            return true;
        }
        return false;
    }

    public void requestRefreshHeader() {
        refreshHeader();
        invalidate(new Rect(0, 0, mHeaderWidth, mHeaderHeight));
    }

    protected void refreshHeader() {
        if (mHeaderView == null) {
            return;
        }
        int firstVisiblePos = getFirstVisiblePosition();
        int pos = firstVisiblePos + 1;
        int firstVisibleGroupPos = getPackedPositionGroup(getExpandableListPosition(firstVisiblePos));
        int group = getPackedPositionGroup(getExpandableListPosition(pos));

        if (group == firstVisibleGroupPos + 1) {
            View view = getChildAt(1);
            if (view == null) {
                return;
            }
            if (view.getTop() <= mHeaderHeight) {
                int delta = mHeaderHeight - view.getTop();
                mHeaderView.layout(0, -delta, mHeaderWidth, mHeaderHeight
                        - delta);
            } else {
                mHeaderView.layout(0, 0, mHeaderWidth, mHeaderHeight);
            }
        } else {
            mHeaderView.layout(0, 0, mHeaderWidth, mHeaderHeight);
        }

        if (mHeaderUpdateListener != null) {
            mHeaderUpdateListener.updateHeader(mHeaderView,
                    firstVisibleGroupPos);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (totalItemCount > 0) {
            refreshHeader();
        }
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                    totalItemCount);
        }
    }

    @Override
    public boolean expandGroup(int groupPos) {
        boolean result = false;
        ExpandableListAdapter adapter = getExpandableListAdapter();
        if (adapter == null)
            return result;
        GroupItem group = (GroupItem) adapter.getGroup(groupPos);
        if (group != null) {
            boolean expand = group.isExpand();
            if (!expand) {
                group.setIsExpand(true);
            }
            result = super.expandGroup(groupPos);
        }
        return result;
    }

    @Override
    public boolean collapseGroup(int groupPos) {
        boolean result = false;
        ExpandableListAdapter adapter = getExpandableListAdapter();
        if (adapter == null)
            return result;
        GroupItem group = (GroupItem) adapter.getGroup(groupPos);
        if (group != null) {
            boolean expand = group.isExpand();
            if (expand) {
                group.setIsExpand(false);
            }
            result = super.collapseGroup(groupPos);
        }
        return result;
    }

    /**
     * 打开所有组
     */
    public void expandAllGroups() {
        ExpandableListAdapter adapter = getExpandableListAdapter();
        if (adapter == null) {
            return;
        }
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandGroup(i);
        }
    }

}