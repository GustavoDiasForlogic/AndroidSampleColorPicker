package net.forlogic.samplecolorpicker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import net.forlogic.colorpicker.ColorPickerDialog;


@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {

    public static final int[] COLORS_OPTIONS = {
            0xFFF4CCCC, 0xFFEA9999, 0xFFE06666, 0xFFCC0000, 0xFF990000,
            0xFFFCE5CD, 0xFFF9CB9C, 0xFFF6B26B, 0xFFE69138, 0xFFB45F06,
            0xFFFFF2CC, 0xFFFFE599, 0xFFFFD966, 0xFFF1C232, 0xFFBF9000,
            0xFFD9EAD3, 0xFFB6D7A8, 0xFF93C47D, 0xFF6AA84F, 0xFF38761D,
            0xFFD0E0E3, 0xFFA2C4C9, 0xFF76A5AF, 0xFF45818E, 0xFF134F5C,
            0xFFCFE2F3, 0xFF9FC5E8, 0xFF6FA8DC, 0xFF3D85C6, 0xFF0B5394,
            0xFFD9D2E9, 0xFFB4A7D6, 0xFF8E7CC3, 0xFF674EA7, 0xFF351C75,
            0xFFEAD1DC, 0xFFD5A6BD, 0xFFC27BA0, 0xFFA64D79, 0xFF741B47,
            Color.WHITE
    };

    private int currentColor = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FrameLayout layPaintColor = (FrameLayout) findViewById(R.id.layPaintColor);
        final Button bChooseColor = (Button) findViewById(R.id.bChooseColor);

        bChooseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ColorPickerDialog(MainActivity.this,
                        COLORS_OPTIONS, // colors
                        currentColor, // Selected color
                        new ColorPickerDialog.OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int color) {
                                layPaintColor.setBackgroundColor(color);
                                currentColor = color;
                            }
                        },
                        5, // num columns
                        "Escolha uma cor", // title
                        R.style.AppCompatAlertDialogStyle // style
                ).show();
            }
        });
    }
}