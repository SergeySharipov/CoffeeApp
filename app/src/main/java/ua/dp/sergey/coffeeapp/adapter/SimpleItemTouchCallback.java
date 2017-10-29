package ua.dp.sergey.coffeeapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import ua.dp.sergey.coffeeapp.R;

/**
 * Created by Sergey-PC on 29.10.2017.
 */

public class SimpleItemTouchCallback extends ItemTouchHelper.SimpleCallback {
    private Context mContext;
    private ISimpleItemTouchCallback mISimpleItemTouchCallback;
    private Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
    private SwipeDrawHelper mSwipeDrawHelper;
    private Bitmap mIconEdit, mIconDelete, mTempIcon;
    private int mGreenColor, mRedColor, mTempColor;

    public SimpleItemTouchCallback(Context context,
                                   ISimpleItemTouchCallback iSimpleItemTouchCallback) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mContext = context;
        this.mISimpleItemTouchCallback = iSimpleItemTouchCallback;

        mSwipeDrawHelper = new SwipeDrawHelper();

        mIconEdit = BitmapFactory.decodeResource(mContext.getResources(),
                android.R.drawable.ic_menu_edit);
        mIconDelete = BitmapFactory.decodeResource(mContext.getResources(),
                android.R.drawable.ic_menu_delete);
        mGreenColor = mContext.getResources().getColor(R.color.Green);
        mRedColor = mContext.getResources().getColor(R.color.Red);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT) {
            mISimpleItemTouchCallback.removeItem(position);
        } else {
            mISimpleItemTouchCallback.editItem(position);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            mSwipeDrawHelper.onStartDraw((int) dX, viewHolder);

            if (dX > 0) {
                mTempIcon = mIconEdit;
                mTempColor = mGreenColor;
                drawSwipeBackground(c, true);
            } else if (dX < 0) {
                mTempIcon = mIconDelete;
                mTempColor = mRedColor;
                drawSwipeBackground(c, false);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private void drawSwipeBackground(Canvas c, boolean swipeFrRightToLeft) {
        mPaint.setColor(mTempColor);
        mPaint.setAlpha(mSwipeDrawHelper.getAlpha(swipeFrRightToLeft));

        c.drawRoundRect(mSwipeDrawHelper.getBackgroundDest(swipeFrRightToLeft),
                10, 10, mPaint);
        c.drawBitmap(mTempIcon, null,
                mSwipeDrawHelper.getIconDest(swipeFrRightToLeft), mPaint);
    }
}
