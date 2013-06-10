package com.weigreen.poker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;

public class MainActivity extends Activity {


    private TFHClientSocket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        socket = new TFHClientSocket(MainActivity.this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void haveNewData(TFHBridgeMain main){
        //here
    }

    public void haveNewDataRoom(TFHBridgeMain main){

    }

    public void switchToMakeRoom(){

    }

    /**
     * Make new room done
     * @param view
     */
    public void makeRoomSubmit(View view){

    }

    /**
     * Use this function to make room
     */
    private void runMakeRoom() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(
                R.layout.alert_dialog_text_entry, null);
        new AlertDialog.Builder(MainActivity.this)
                // .setIconAttribute(android.R.attr.alertDialogIcon)
                .setTitle(R.string.make_room_title)
                        // TODO check!!!
                .setView(textEntryView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        EditText text = (EditText) textEntryView
                                .findViewById(R.id.code_input);
                        String code = text.getText().toString();
                        Context context = getApplicationContext();

                        socket.makeRoom(code);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

								/* User clicked cancel so do some stuff */
                            }
                        }).create().show();

    }


}
