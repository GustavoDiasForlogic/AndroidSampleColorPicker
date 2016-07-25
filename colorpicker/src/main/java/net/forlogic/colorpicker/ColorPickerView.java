package net.forlogic.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo.dias on 01/04/2016.
 */
public class ColorPickerView extends GridView {

    private List<Integer> mColors = new ArrayList<>();
    private int mSelectedColorIndex = 0;
    private OnColorClickListener mOnColorClickListener;
    private ColorPickerViewAdapter mAdapter = new ColorPickerViewAdapter();

    public ColorPickerView(Context context) {
        super(context);
        init();
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setAdapter(mAdapter);
    }

    public void setOnColorClickListener(OnColorClickListener l) {
        mOnColorClickListener = l;
    }

    public void setColors(int[] colors, int selectedColor) {
        mColors = new ArrayList<>();
        for (int color : colors)
            mColors.add(color);

        mSelectedColorIndex = -1;
        int i = 0;
        for (int color : colors) {
            if (color == selectedColor) {
                mSelectedColorIndex = i;
                break;
            }
            i++;
        }
        if (mSelectedColorIndex == -1)
            addColor(selectedColor);

        mAdapter.notifyDataSetChanged();
    }

    public void setSelectedColor(int selectedColor) {
        int i = 0;
        mSelectedColorIndex = -1;
        for (int color : mColors) {
            if (color == selectedColor) {
                mSelectedColorIndex = i;
                break;
            }
            i++;
        }
        if (mSelectedColorIndex == -1)
            addColor(selectedColor);

        mAdapter.notifyDataSetChanged();
    }

    private void addColor(int color) {
        mSelectedColorIndex = -1;
        int i = 0;
        for (int c : mColors) {
            if (c == color) {
                mSelectedColorIndex = i;
                break;
            }
            i++;
        }
        if (mSelectedColorIndex == -1) {
            mSelectedColorIndex = mColors.size();
            mColors.add(color);
            mAdapter.notifyDataSetChanged();
        }
    }


    private class ColorPickerViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mColors.size() + 1;
        }

        public boolean isCustomColorSelector(int position) {
            return position == mColors.size();
        }

        @Override
        public Integer getItem(int position) {
            return mColors.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public ColorPickerOptionImageView getView(final int position, View convertView, ViewGroup parent) {
            if (isCustomColorSelector(position)) {
                ColorPickerOptionImageView imgCustomColor = new ColorPickerOptionImageView(
                        getContext(),
                        false,
                        Color.LTGRAY) {
                    @Override
                    public void setImageDrawable(Drawable drawable) {
                        super.setImageDrawable(new LayerDrawable(new Drawable[] {
                                drawable,
                                new InsetDrawable(getContext().getResources().getDrawable(R.drawable.ic_more_colors), 5)
                        }));
                    }
                };
                imgCustomColor.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomColorPickerDialog d = new CustomColorPickerDialog(getContext(),
                                !mColors.isEmpty() ? mColors.get(mSelectedColorIndex) : Color.WHITE, // initial color
                                new CustomColorPickerDialog.OnColorSelectedListener() {
                                    @Override
                                    public void colorSelected(Integer color) {
                                        if (color != null) {
                                            addColor(color);
                                        }
                                    }
                                });
                        d.show();
                    }
                });
                return imgCustomColor;

            } else {
                ColorPickerOptionImageView imgColor = new ColorPickerOptionImageView(
                        getContext(),
                        position == mSelectedColorIndex,
                        getItem(position));

                final int index = position;
                final int color = getItem(position);
                imgColor.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSelectedColorIndex = index;
                        if (mOnColorClickListener != null)
                            mOnColorClickListener.onColorClick(index, color);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                return imgColor;
            }
        }
    }
}
