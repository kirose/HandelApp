package com.handel.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.handel.handel.R;

/**
 * Created by Marco Antonio on 04/03/2018.
 */

public final class UtilLayout {
    public static void showPromptMsg(Context context, String msg){
        // get prompts.xml view
        Log.i("showPromptMsg",msg);
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        TextView textMsg = (TextView) promptsView.findViewById(R.id.prompt_msg);
        textMsg.setText(msg);
        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",(DialogInterface dialog, int id) -> {dialog.cancel();});

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
