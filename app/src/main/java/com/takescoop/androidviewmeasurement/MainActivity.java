package com.takescoop.androidviewmeasurement;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.view_a) RelativeLayout viewA;
    @BindView(R.id.view_b) LinearLayout viewB;
    @BindView(R.id.view_c) TextView viewC;

    @BindView(R.id.hidden_view) TextView hiddenView;
    @BindView(R.id.partially_hidden) TextView partiallyHiddenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        printViewPositions(viewA, viewB, viewC);
    }

    private void printViewPositions(View viewA, View viewB, View viewC) {
        int[] viewALocationInWindow = new int[2];
        int[] viewBLocationInWindow = new int[2];
        int[] viewCLocationInWindow = new int[2];
        viewA.getLocationInWindow(viewALocationInWindow);
        viewB.getLocationInWindow(viewBLocationInWindow);
        viewC.getLocationInWindow(viewCLocationInWindow);

        int[] viewALocationOnScreen = new int[2];
        int[] viewBLocationOnScreen = new int[2];
        int[] viewCLocationOnScreen = new int[2];
        viewA.getLocationOnScreen(viewALocationOnScreen);
        viewB.getLocationOnScreen(viewBLocationOnScreen);
        viewC.getLocationOnScreen(viewCLocationOnScreen);

        float Ax = viewA.getX();
        float Ay = viewA.getY();
        float Bx = viewB.getX();
        float By = viewB.getY();
        float Cx = viewC.getX();
        float Cy = viewC.getY();

        Rect viewAGlobalRect = new Rect();
        Rect viewBGlobalRect = new Rect();
        Rect viewCGlobalRect = new Rect();
        viewA.getGlobalVisibleRect(viewAGlobalRect);
        viewB.getGlobalVisibleRect(viewBGlobalRect);
        viewC.getGlobalVisibleRect(viewCGlobalRect);

        Rect viewALocalRect = new Rect();
        Rect viewBLocalRect = new Rect();
        Rect viewCLocalRect = new Rect();
        viewA.getLocalVisibleRect(viewALocalRect);
        viewB.getLocalVisibleRect(viewBLocalRect);
        viewC.getLocalVisibleRect(viewCLocalRect);

        Log.d(TAG, "printViewPositions LocationInWindow() ViewA: " + Arrays.toString(viewALocationInWindow)
                + ", ViewB: " + Arrays.toString(viewBLocationInWindow) + ", ViewC: " + Arrays.toString(viewCLocationInWindow));
        Log.d(TAG, "printViewPositions LocationOnScreen() ViewA: " + Arrays.toString(viewALocationOnScreen)
                + ", ViewB: " + Arrays.toString(viewBLocationOnScreen) + ", ViewC: " + Arrays.toString(viewCLocationOnScreen));
        Log.d(TAG, "printViewPositions GetX(), GetY() ViewA: " + Ax + "," + Ay
                + ", ViewB: " + Bx + "," + By + ", ViewC: " + Cx + "," + Cy);
        Log.d(TAG, "printViewPositions GlobalRect ViewA: " + viewAGlobalRect + ", ViewB: " + viewBGlobalRect + ", ViewC " + viewCGlobalRect);
        Log.d(TAG, "printViewPositions LocalRect ViewA: " + viewALocalRect + ", ViewB: " + viewBLocalRect + ", ViewC " + viewCLocalRect);

        Rect hiddenGlobalRect = new Rect();
        Rect partiallyHiddenGlobalRect = new Rect();
        Rect hiddenLocalRect = new Rect();
        Rect partiallyHiddenLocalRect = new Rect();
        Log.d(TAG, "printViewPositions hidden global" + hiddenView.getGlobalVisibleRect(hiddenGlobalRect));
        Log.d(TAG, "printViewPositions hidden local" + hiddenView.getLocalVisibleRect(hiddenLocalRect));
        Log.d(TAG, "printViewPositions partially hidden global" + partiallyHiddenView.getGlobalVisibleRect(partiallyHiddenGlobalRect));
        Log.d(TAG, "printViewPositions partially hidden global" + partiallyHiddenView.getLocalVisibleRect(partiallyHiddenLocalRect));

        Log.d(TAG, "printViewPositions GlobalRect hidden: " + hiddenGlobalRect + ", partially hidden: " + partiallyHiddenGlobalRect);
        Log.d(TAG, "printViewPositions LocalRect hidden: " + hiddenLocalRect + ", partially hidden: " + partiallyHiddenLocalRect);
    }

    @OnClick(R.id.update_button)
    protected void update() {
        printViewPositions(viewA, viewB, viewC);
    }

    @OnClick(R.id.show_dialog_button)
    protected void showDialog() {
        getDialog(this).show();
    }

    public Dialog getDialog(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_main);

        final View viewA = ButterKnife.findById(dialog, R.id.view_a);
        final View viewB = ButterKnife.findById(dialog, R.id.view_b);
        final View viewC = ButterKnife.findById(dialog, R.id.view_c);

        Button updateButton = ButterKnife.findById(dialog, R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                printViewPositions(viewA, viewB, viewC);
            }
        });


        return dialog;
    }
}
