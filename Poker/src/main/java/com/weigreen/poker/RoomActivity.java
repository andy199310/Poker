package com.weigreen.poker;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.os.Handler;

import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;

import java.util.List;

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

    private ImageButton card_one;
    private ImageButton card_two;
    private ImageButton card_three;
    private ImageButton card_four;
    private ImageButton card_five;
    private ImageButton card_six;
    private ImageButton card_seven;
    private ImageButton card_eight;
    private ImageButton card_nine;
    private ImageButton card_ten;
    private ImageButton card_eleven;
    private ImageButton card_twelve;
    private ImageButton card_thirteen;

    private ImageButton opposite;
    private ImageButton left;
    private ImageButton right;
    private ImageButton home;

    private short suit;
    private short heap;

    private Handler handler = new Handler();

    //private
    private TFHClientRoomSocket roomSocket;

    private int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCallUI();

        port = getIntent().getIntExtra("port", 0);
        roomSocket = new TFHClientRoomSocket(port, this);
        roomSocket.start();
    }

    private void setCallUI() {

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

    private void setGameUI() {

        setContentView(R.layout.activity_room_game);

        card_one = (ImageButton)findViewById(R.id.card_one);
        card_one.setOnClickListener(buttonOnClick);
        card_two = (ImageButton)findViewById(R.id.card_two);
        card_two.setOnClickListener(buttonOnClick);
        card_three = (ImageButton)findViewById(R.id.card_three);
        card_three.setOnClickListener(buttonOnClick);
        card_four = (ImageButton)findViewById(R.id.card_four);
        card_four.setOnClickListener(buttonOnClick);
        card_five = (ImageButton)findViewById(R.id.card_five);
        card_five.setOnClickListener(buttonOnClick);
        card_six = (ImageButton)findViewById(R.id.card_six);
        card_six.setOnClickListener(buttonOnClick);
        card_seven = (ImageButton)findViewById(R.id.card_seven);
        card_seven.setOnClickListener(buttonOnClick);
        card_eight = (ImageButton)findViewById(R.id.card_eight);
        card_eight.setOnClickListener(buttonOnClick);
        card_nine = (ImageButton)findViewById(R.id.card_nine);
        card_nine.setOnClickListener(buttonOnClick);
        card_ten = (ImageButton)findViewById(R.id.card_ten);
        card_ten.setOnClickListener(buttonOnClick);
        card_eleven = (ImageButton)findViewById(R.id.card_eleven);
        card_eleven.setOnClickListener(buttonOnClick);
        card_twelve = (ImageButton)findViewById(R.id.card_twelve);
        card_twelve.setOnClickListener(buttonOnClick);
        card_thirteen = (ImageButton)findViewById(R.id.card_thirteen);
        card_thirteen.setOnClickListener(buttonOnClick);

        opposite = (ImageButton)findViewById(R.id.opposite);
        left = (ImageButton)findViewById(R.id.left);
        right = (ImageButton)findViewById(R.id.right);
        home = (ImageButton)findViewById(R.id.home);
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
                case R.id.oneButton:
                    heap = 1;
                case R.id.twoButton:
                    heap = 2;
                case R.id.threeButton:
                    heap = 3;
                case R.id.fourButton:
                    heap = 4;
                case R.id.fiveButton:
                    heap = 5;
                case R.id.sixButton:
                    heap = 6;
                case R.id.sevenButton:
                    heap = 7;
                case R.id.eightButton:
                    heap = 8;
                case R.id.nineButton:
                    heap = 9;
                case R.id.tenButton:
                    heap = 10;
                case R.id.callButton:
                    if(heap != 0 && suit != 0){
                        //call
                    }
                case R.id.passButton:
                    heap = 0;

                case R.id.card_one:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_two:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_three:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_four:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_five:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_six:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_seven:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_eight:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_nine:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_ten:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_eleven:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_twelve:
                    card_one.setImageResource(R.drawable.c000);

                case R.id.card_thirteen:
                    card_one.setImageResource(R.drawable.c000);

            }
        }
    };
}
