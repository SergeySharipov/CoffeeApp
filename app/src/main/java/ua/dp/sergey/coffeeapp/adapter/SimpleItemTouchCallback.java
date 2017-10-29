package ua.dp.sergey.coffeeapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import ua.dp.sergey.coffeeapp.R;

/**
 * Created by Sergey-PC on 29.10.2017.
 */

public class SimpleItemTouchCallback extends ItemTouchHelper.SimpleCallback {
    private Context mContext;
    private ISimpleItemTouchCallback mISimpleItemTouchCallback;
    private Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);

    public SimpleItemTouchCallback(Context context,
                                   ISimpleItemTouchCallback iSimpleItemTouchCallback) {
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mContext = context;
        this.mISimpleItemTouchCallback = iSimpleItemTouchCallback;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT){
            mISimpleItemTouchCallback.removeItem(position);
        } else {
            mISimpleItemTouchCallback.editItem(position);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Bitmap icon;
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            if(dX > 0){
                mPaint.setColor(mContext.getResources().getColor(R.color.Green));
                int alpha=(int)dX/2;
                if(alpha<255)
                    mPaint.setAlpha(alpha);
                RectF background = new RectF(
                        (float) itemView.getLeft(),
                        (float) itemView.getTop(),
                        (float)(itemView.getRight()/2)+dX,
                        (float) itemView.getBottom());
                c.drawRoundRect(background,10,10, mPaint);
                icon = BitmapFactory.decodeResource(mContext.getResources(),
                        android.R.drawable.ic_menu_edit);
                RectF icon_dest = new RectF(
                        (float) itemView.getLeft() + width ,
                        (float) itemView.getTop() + width,
                        (float) itemView.getLeft()+ 2*width,
                        (float)itemView.getBottom() - width);
                c.drawBitmap(icon,null,icon_dest, mPaint);
            } else if(dX < 0) {
                mPaint.setColor(mContext.getResources().getColor(R.color.Red));
                int alpha=(int)(dX*-1)/2;
                if(alpha<255)
                    mPaint.setAlpha(alpha);
                RectF background = new RectF(
                        (float) (itemView.getRight()/2)+dX,
                        (float) itemView.getTop(),
                        (float) itemView.getRight(),
                        (float) itemView.getBottom());
                c.drawRoundRect(background,10,10, mPaint);
                icon = BitmapFactory.decodeResource(mContext.getResources(),
                        android.R.drawable.ic_menu_delete);
                RectF icon_dest = new RectF(
                        (float) itemView.getRight() - 2*width ,
                        (float) itemView.getTop() + width,
                        (float) itemView.getRight() - width,
                        (float)itemView.getBottom() - width);
                c.drawBitmap(icon,null,icon_dest, mPaint);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
