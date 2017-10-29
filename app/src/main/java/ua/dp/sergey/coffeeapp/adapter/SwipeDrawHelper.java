package ua.dp.sergey.coffeeapp.adapter;

import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sergey-PC on 29.10.2017.
 */

public class SwipeDrawHelper {
    private View mItemView;
    private int mDx;
    private float mWidth;

    void onStartDraw(int dX, RecyclerView.ViewHolder viewHolder) {
        this.mDx = dX;
        mItemView = viewHolder.itemView;
        float mHeight = (float) mItemView.getBottom() - (float) mItemView.getTop();
        mWidth = mHeight / 3;
    }

    RectF getBackgroundDest(boolean mSwipeFrRightToLeft) {
        if (mSwipeFrRightToLeft) return new RectF(
                (float) mItemView.getLeft(),
                (float) mItemView.getTop(),
                (float) (mItemView.getRight() / 2) + mDx,
                (float) mItemView.getBottom());
        else return new RectF(
                (float) (mItemView.getRight() / 2) + mDx,
                (float) mItemView.getTop(),
                (float) mItemView.getRight(),
                (float) mItemView.getBottom());
    }

    RectF getIconDest(boolean mSwipeFrRightToLeft) {
        if (mSwipeFrRightToLeft) return new RectF(
                (float) mItemView.getLeft() + mWidth,
                (float) mItemView.getTop() + mWidth,
                (float) mItemView.getLeft() + 2 * mWidth,
                (float) mItemView.getBottom() - mWidth);
        else return new RectF(
                (float) mItemView.getRight() - 2 * mWidth,
                (float) mItemView.getTop() + mWidth,
                (float) mItemView.getRight() - mWidth,
                (float) mItemView.getBottom() - mWidth);
    }

    int getAlpha(boolean mSwipeFrRightToLeft) {
        int alpha = mDx / 2;
        if (!mSwipeFrRightToLeft)
            alpha *= -1;
        if (alpha < 255)
            return alpha;
        else return 255;
    }
}
