package net.forlogic.colorpicker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by gustavo.dias on 01/04/2016.
 */
public class ColorCircleDrawable extends Drawable {
    private Paint mPaint;
    private int mRadius;

    public ColorCircleDrawable(int color) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(bounds.centerX(), bounds.centerY(), mRadius, mPaint);
        if (mPaint.getColor() == Color.WHITE) {
            mPaint.setStyle(Paint.Style.STROKE);
            int circleColor = mPaint.getColor();
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(bounds.centerX(), bounds.centerY(), mRadius, mPaint);
            mPaint.setColor(circleColor);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mRadius = Math.min(bounds.width(), bounds.height()) / 2;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
