package net.forlogic.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ImageView;

/**
 * Created by gustavo.dias on 01/04/2016.
 */
public class ColorPickerOptionImageView extends ImageView {
    private boolean mSelected;

    public ColorPickerOptionImageView(Context context,
                                      boolean selected,
                                      int color) {
        super(context);
        mSelected = selected;

        Drawable drawable;
        if (mSelected) {
            Drawable checkIcon;
            drawable = new LayerDrawable(new Drawable[] {
                    new ColorCircleDrawable(color), // colored circle
                    checkIcon = new InsetDrawable(getContext().getResources().getDrawable(R.drawable.ic_select_color), 5) // check icon
            });
            checkIcon.setColorFilter(color == Color.WHITE ? Color.BLACK : Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else {
            drawable = new ColorCircleDrawable(color);
        }
        setLayoutParams(new AbsListView.LayoutParams(54, 54));
        setPadding(6, 6, 6, 6);
        this.setImageDrawable(drawable);
        setWillNotDraw(false);
    }

    public ColorPickerOptionImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorPickerOptionImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
        invalidate();
    }

    public boolean isSelected() {
        return mSelected;
    }
}
