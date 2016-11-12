package kku.anaclasmos.easykku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Admin on 11/12/2016.
 */

public class MyAlert {

    //Explicit
    private Context context;
    private int anInt;
    private  String titleString, messageString;

    public MyAlert(Context context, int anInt, String titleString, String messageString) {
        this.context = context;
        this.anInt = anInt;
        this.titleString = titleString;
        this.messageString = messageString;
    }   // Alt + insert =  สร้าง คอนสตัคเตอร์

    public  void myDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);   // ทำให้ไม่สามารถกดปุ่มได้
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();

    }   // my dialog

}   // Main Class
