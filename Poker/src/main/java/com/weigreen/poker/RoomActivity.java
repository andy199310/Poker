package com.weigreen.poker;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;

/**
 * Created by roy on 2013/6/10.
 */
public class RoomActivity extends Activity {

    private Handler handler = new Handler();

    //private
    private TFHClientRoomSocket roomSocket;

    private int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        port = getIntent().getIntExtra("port", 0);
        roomSocket = new TFHClientRoomSocket(port, this);
        roomSocket.start();
    }


    public void haveNewData(TFHBridgeMain main){

    }




    public void showStateWord(final String word){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                final TextView text = (TextView)findViewById(R.id.textView_state);
//                text.setText(word);
//                text.setVisibility(View.VISIBLE);


                        }

        });
    }
}
