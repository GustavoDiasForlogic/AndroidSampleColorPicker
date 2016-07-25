package net.forlogic.colorpicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by gustavo.dias on 04/04/2016.
 */
public class ColorPickerDialog {
    private AlertDialog mDialog;
    private OnColorSelectedListener mColorSelectedListener;
    private int mSelectedColor;

    public ColorPickerDialog(Context context, int[] colors, int selectedColor, OnColorSelectedListener l, int colorsOptionsColumns, CharSequence title, Integer resStyle) {
        mColorSelectedListener = l;

        final ColorPickerView colorPicker = new ColorPickerView(context);
        colorPicker.setColors(colors, selectedColor);
        colorPicker.setOnColorClickListener(new OnColorClickListener() {
            @Override
            public void onColorClick(int index, int color) {
                mSelectedColor = color;
            }
        });
        colorPicker.setNumColumns(4);
        colorPicker.setPadding(15, 15, 15, 15);
        colorPicker.setNumColumns(colorsOptionsColumns);
        mSelectedColor = selectedColor;

        AlertDialog.Builder dialog = resStyle != null ? new AlertDialog.Builder(context, resStyle) : new AlertDialog.Builder(context);
        dialog.setView(colorPicker);
        dialog.setNegativeButton(android.R.string.cancel, null);
        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mColorSelectedListener.onColorSelected(mSelectedColor);
            }
        });
        dialog.setTitle(title);
        mDialog = dialog.create();
    }

    public void show() {
        mDialog.show();
    }


    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }
}