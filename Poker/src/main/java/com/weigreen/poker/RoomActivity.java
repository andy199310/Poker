package com.weigreen.poker;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.os.Parcel;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;

import com.weigreen.ncu.tfh.bridge.Card;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataCard;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataNewPlayer;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataRoom;
import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;
import com.weigreen.ncu.tfh.communication.TFHComm;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataGodCard;


import java.util.ArrayList;
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

    private short inRoomPlayer = 0;

    private short myPlayerID = 100;

    private Card[] myCardArray;

    private ArrayList<ImageButton> handCradArray;

    private ArrayList<ImageButton> callSuitArray;

    private ArrayList<Button> callButtonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.changeViewToWaiting();

        port = getIntent().getIntExtra("port", 0);
        roomSocket = new TFHClientRoomSocket(port, this);
        roomSocket.start();
    }

    private void changeViewToWaiting() {
        setContentView(R.layout.activity_table_waiting);
    }


    /**
     * set Call UI
     */
    private void setCallUI() {

        setContentView(R.layout.activity_room_call);
        handCradArray = new ArrayList<ImageButton>();
        callSuitArray = new ArrayList<ImageButton>();
        callButtonArray = new ArrayList<Button>();


        spadeButton = (ImageButton)findViewById(R.id.spadeButton);
        spadeButton.setOnClickListener(buttonOnClick);
        heartButton = (ImageButton)findViewById(R.id.heartButton);
        heartButton.setOnClickListener(buttonOnClick);
        diamondButton = (ImageButton)findViewById(R.id.diamondButton);
        diamondButton.setOnClickListener(buttonOnClick);
        clubButton = (ImageButton)findViewById(R.id.clubButton);
        clubButton.setOnClickListener(buttonOnClick);

        callSuitArray.add(spadeButton);
        callSuitArray.add(heartButton);
        callSuitArray.add(diamondButton);
        callSuitArray.add(clubButton);


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
        eightButton.setOnClickListener(buttonOnClick);

        callButtonArray.add(oneButton);
        callButtonArray.add(twoButton);
        callButtonArray.add(threeButton);
        callButtonArray.add(fourButton);
        callButtonArray.add(fiveButton);
        callButtonArray.add(sixButton);
        callButtonArray.add(sevenButton);
        callButtonArray.add(eightButton);
        callButtonArray.add(eightButton);



        callButton = (Button)findViewById(R.id.callButton);
        callButton.setOnClickListener(buttonOnClick);
        passButton = (Button)findViewById(R.id.passButton);
        passButton.setOnClickListener(buttonOnClick);

        card_one = (ImageButton)findViewById(R.id.card_one);
        setClickableFalse(card_one);
        card_two = (ImageButton)findViewById(R.id.card_two);
        setClickableFalse(card_two);
        card_three = (ImageButton)findViewById(R.id.card_three);
        setClickableFalse(card_three);
        card_four = (ImageButton)findViewById(R.id.card_four);
        setClickableFalse(card_four);
        card_five = (ImageButton)findViewById(R.id.card_five);
        setClickableFalse(card_five);
        card_six = (ImageButton)findViewById(R.id.card_six);
        setClickableFalse(card_six);
        card_seven = (ImageButton)findViewById(R.id.card_seven);
        setClickableFalse(card_seven);
        card_eight = (ImageButton)findViewById(R.id.card_eight);
        setClickableFalse(card_eight);
        card_nine = (ImageButton)findViewById(R.id.card_nine);
        setClickableFalse(card_nine);
        card_ten = (ImageButton)findViewById(R.id.card_ten);
        setClickableFalse(card_ten);
        card_eleven = (ImageButton)findViewById(R.id.card_eleven);
        setClickableFalse(card_eleven);
        card_twelve = (ImageButton)findViewById(R.id.card_twelve);
        setClickableFalse(card_twelve);
        card_thirteen = (ImageButton)findViewById(R.id.card_thirteen);
        setClickableFalse(card_thirteen);

        handCradArray.add(card_one);
        handCradArray.add(card_two);
        handCradArray.add(card_three);
        handCradArray.add(card_four);
        handCradArray.add(card_five);
        handCradArray.add(card_six);
        handCradArray.add(card_seven);
        handCradArray.add(card_eight);
        handCradArray.add(card_nine);
        handCradArray.add(card_ten);
        handCradArray.add(card_eleven);
        handCradArray.add(card_twelve);
        handCradArray.add(card_thirteen);
        setMyHandCard();
		setCallSuitOnOff(false);
		setCallNumberOnOff(false);
		
    }

    /**
     * set Game UI
     */
    private void setGameUI() {

        setContentView(R.layout.activity_room_game);

        card_one = (ImageButton)findViewById(R.id.card_one);
        setClickableTrue(card_one);
        card_one.setOnClickListener(buttonOnClick);
        card_two = (ImageButton)findViewById(R.id.card_two);
        setClickableTrue(card_two);
        card_two.setOnClickListener(buttonOnClick);
        card_three = (ImageButton)findViewById(R.id.card_three);
        setClickableTrue(card_three);
        card_three.setOnClickListener(buttonOnClick);
        card_four = (ImageButton)findViewById(R.id.card_four);
        setClickableTrue(card_four);
        card_four.setOnClickListener(buttonOnClick);
        card_five = (ImageButton)findViewById(R.id.card_five);
        setClickableTrue(card_five);
        card_five.setOnClickListener(buttonOnClick);
        card_six = (ImageButton)findViewById(R.id.card_six);
        setClickableTrue(card_six);
        card_six.setOnClickListener(buttonOnClick);
        card_seven = (ImageButton)findViewById(R.id.card_seven);
        setClickableTrue(card_seven);
        card_seven.setOnClickListener(buttonOnClick);
        card_eight = (ImageButton)findViewById(R.id.card_eight);
        setClickableTrue(card_eight);
        card_eight.setOnClickListener(buttonOnClick);
        card_nine = (ImageButton)findViewById(R.id.card_nine);
        setClickableTrue(card_nine);
        card_nine.setOnClickListener(buttonOnClick);
        card_ten = (ImageButton)findViewById(R.id.card_ten);
        setClickableTrue(card_ten);
        card_ten.setOnClickListener(buttonOnClick);
        card_eleven = (ImageButton)findViewById(R.id.card_eleven);
        setClickableTrue(card_eleven);
        card_eleven.setOnClickListener(buttonOnClick);
        card_twelve = (ImageButton)findViewById(R.id.card_twelve);
        setClickableTrue(card_twelve);
        card_twelve.setOnClickListener(buttonOnClick);
        card_thirteen = (ImageButton)findViewById(R.id.card_thirteen);
        setClickableTrue(card_thirteen);
        card_thirteen.setOnClickListener(buttonOnClick);

        opposite = (ImageButton)findViewById(R.id.opposite);
        setClickableFalse(opposite);
        left = (ImageButton)findViewById(R.id.left);
        setClickableFalse(left);
        right = (ImageButton)findViewById(R.id.right);
        setClickableFalse(right);
        home = (ImageButton)findViewById(R.id.home);
        setClickableFalse(home);

    }

    public void haveNewData(final TFHBridgeMain main){
        handler.post(new Runnable() {
            @Override
            public void run() {
                //TODO main thing
                short command = main.getCommand();
                Log.d("(R)SERVER COMMAND:", String.valueOf(command));

                switch(command){
                    case TFHComm.ROOM_NEW_PLAYER:
                        Log.d("(R)ACTION", "room new player");
                        TFHBridgeDataNewPlayer tfhBridgeDataNewPlayer = (TFHBridgeDataNewPlayer) main.getData();
                        inRoomPlayer = (short) (tfhBridgeDataNewPlayer.getNewPlayerNumber() + 1);
                        Log.d("(R)IN ROOM PLAYER", String.valueOf(inRoomPlayer));
                        changeWaitingPerson();
                        if(myPlayerID == 100){
                            myPlayerID = (short) (inRoomPlayer-1);
                            Log.d("My player id", String.valueOf(myPlayerID));
                            if(myPlayerID == 3){
                                // deal card when all player is in
                                roomSocket.dealCard();
                            }
                        }
                        break;
                    case TFHComm.CARD_DATA:
                        Log.d("(R)ACTION", "card data(deal card)");
                        TFHBridgeDataCard tfhBridgeDataCard = (TFHBridgeDataCard) main.getData();
                        myCardArray = tfhBridgeDataCard.getCardData()[myPlayerID];
                        for(int i=0; i<myCardArray.length; i++){
                            Log.d("(R)DEAL CARD", "my card:" + myCardArray[i].getId());
                        }
                        setCallUI();

                        break;
					case TFHComm.GOD_CARD_DATA:
						Log.d("(R)ACTIOM", "get god card data");
						TFHBridgeDataGodCard tfhBridgeDataGodCard = (TFHBridgeDataGodCard) main.getData();
						String godCardCommand = tfhBridgeDataGodCard.getCommand();
						if (godCardCommand.equals("KEEP")){
							//con
						}else{
							//finish
						}
						break;
                }

            }
        });
    }

    /**
     * change the in room player
     */
    private void changeWaitingPerson() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView tableDisplayWord = (TextView) findViewById(R.id.table_display_word);
                tableDisplayWord.setText(getString(R.string.table_waiting_word, inRoomPlayer));
            }
        });
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
                //case R.id.tenButton:
                //    heap = 10;
                case R.id.callButton:
                    if(heap != 0 && suit != 0){
                        //call
                    }
                case R.id.passButton:
                    heap = 0;

                case R.id.card_one:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_one);

                case R.id.card_two:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_two);

                case R.id.card_three:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_three);

                case R.id.card_four:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_four);

                case R.id.card_five:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_five);

                case R.id.card_six:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_six);

                case R.id.card_seven:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_seven);

                case R.id.card_eight:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_eight);

                case R.id.card_nine:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_nine);

                case R.id.card_ten:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_ten);

                case R.id.card_eleven:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_eleven);

                case R.id.card_twelve:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_twelve);

                case R.id.card_thirteen:
                    card_one.setImageResource(R.drawable.c000);
                    setClickableFalse(card_thirteen);

            }
        }
    };

    private void setClickableFalse(ImageButton imageButton) {

        imageButton.setClickable(false);
    }

    private void setClickableTrue(ImageButton imageButton) {

        imageButton.setClickable(true);
    }
	
	private void setCallSuitOnOff(boolean onOff){
		for (int i=0; i<callSuitArray.size(); i++){
			callSuitArray.get(i).setClickable(onOff);
		}
	}
	
	private void setCallNumberOnOff(boolean onOff){
		for (int i=0; i<callButtonArray.size(); i++){
			callButtonArray.get(i).setClickable(onOff);
		}
	}

    private void setMyHandCard(){
        for (int i=0; i<handCradArray.size(); i++){
            Log.d("(R)SET CARD", String.valueOf(i));
            handCradArray.get(i).setImageResource(Functions.cardToDrawableID(myCardArray[i].getId()));
        }
    }
}
