package com.example.mysubmission3.function;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import com.example.mysubmission3.R;

public class AlertDialogManager {

    public void showDialog(final Context ctx, String title, String message, Boolean sts){
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setTitle(title);
        alert.setMessage(message);
        if(sts != null){
            alert.setIcon(R.drawable.baseline_search_black_24dp);
            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    ctx.startActivity(i);
                }
            });

            alert.create().show();
        }
    }
}
