package com.seeme.daniel.seepic.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.seeme.daniel.seepic.R;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/9 14:54
 */
public class DialogUtils {

    public static Dialog getLoadingDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.dialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setDimAmount(0.1f);
        return dialog;
    }
}
