package com.weigreen.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.os.Handler;

import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;

/**
 * Created by roy on 2013/6/10.
 */
public class RoomActivity extends Activity {

    private ImageButton spadeButton;
    private ImageButton heartButton;
    private ImageButton diamondButton;
    private ImageButton clubButton;

    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;

    private Button callButton;
    private Button passButton;

    private short suit;
    private short heap;

    private Handler handler = new Handler();

    //private
    private TFHClientRoomSocket roomSocket;

    private int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setUI();

        port = getIntent().getIntExtra("port", 0);
        roomSocket = new TFHClientRoomSocket(port, this);
        roomSocket.start();
    }

    private void setUI() {

        setContentView(R.layout.activity_room_call);

        spadeButton = (ImageButton)findViewById(R.id.spadeButton);
        spadeButton.setOnClickListener(buttonOnClick);
        heartButton = (ImageButton)findViewById(R.id.heartButton);
        heartButton.setOnClickListener(buttonOnClick);
        diamondButton = (ImageButton)findViewById(R.id.diamondButton);
        diamondButton.setOnClickListener(buttonOnClick);
        clubButton = (ImageButton)findViewById(R.id.clubButton);
        clubButton.setOnClickListener(buttonOnClick);

        oneButton = (Button)findViewById(R.id.oneButton);
        oneButton.setOnClickListener(buttonOnClick);
        twoButton = (Button)findViewById(R.id.twoButton);
        twoButton.setOnClickListener(buttonOnClick);
        threeButton = (Button)findViewById(R.id.threeButton);
        threeButton.setOnClickListener(buttonOnClick);
        fourButton = (Button)findViewById(R.id.fourButton);
        fourButton.setOnClickListener(buttonOnClick);
        fiveButton = (Button)findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(buttonOnClick);
        sixButton = (Button)findViewById(R.id.sixButton);
        sixButton.setOnClickListener(buttonOnClick);
        sevenButton = (Button)findViewById(R.id.sevenButton);
        sevenButton.setOnClickListener(buttonOnClick);
        eightButton = (Button)findViewById(R.id.eightButton);
        eightButton.setOnClickListener(buttonOnClick);
        nineButton = (Button)findViewById(R.id.nineButton);
        nineButton.setOnClickListener(buttonOnClick);

        callButton = (Button)findViewById(R.id.callButton);
        callButton.setOnClickListener(buttonOnClick);
        passButton = (Button)findViewById(R.id.passButton);
        passButton.setOnClickListener(buttonOnClick);
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

    private Button.OnClickListener buttonOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.spadeButton:
                    suit = 1;
                case R.id.heartButton:
                    suit = 2;
                case R.id.diamondButton:
                    suit = 3;
                case R.id.clubButton:
                    suit = 4;
            }
        }
    };
}
