package com.weigreen.poker;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.weigreen.ncu.tfh.bridge.TFHBridgeDataNewPlayer;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataRoom;
import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;
import com.weigreen.ncu.tfh.communication.TFHComm;

/**
 * Created by asus on 2013/6/13.
 */
public class TableActivity extends Activity {

    private final int GUI_STATE_WAITING = 100;

    private TFHClientRoomTableSocket roomSocket;

    private int port;

    private Handler handler = new Handler();

    private short inRoomPlayer = 0;

    private short[] teamScore = new short[2];

    private short[] tableCard = new short[4];

    private short initPlayer;

    private short nowPlayer;

    private ImageView[] cardImageViewArray;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        port = getIntent().getIntExtra("port", 0);
        roomSocket = new TFHClientRoomTableSocket(port, this);
        roomSocket.start();

        changeView(GUI_STATE_WAITING);
        changeWaitingPerson();
    }

    private void changeWaitingPerson() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView tableDisplayWord = (TextView) findViewById(R.id.table_display_word);
                tableDisplayWord.setText(getString(R.string.table_waiting_word, inRoomPlayer));
            }
        });
    }

    /**
     * The main method to control all the basic view
     * @param state
     */
    private void changeView(int state){
        switch(state){
            case GUI_STATE_WAITING:
                changeViewToWaiting();
                break;
        }
    }

    private void changeViewToWaiting() {
        setContentView(R.layout.activity_table_waiting);
    }

    private void changeViewToGame(){
        setContentView(R.layout.activity_room_game);
        cardImageViewArray[0] = (ImageView) findViewById(R.id.table_card_0);
        cardImageViewArray[1] = (ImageView) findViewById(R.id.table_card_1);
        cardImageViewArray[2] = (ImageView) findViewById(R.id.table_card_2);
        cardImageViewArray[3] = (ImageView) findViewById(R.id.table_card_3);
    }


    /**
     * When socket receive new data, call this method.
     * @param main - TFHBridgeMain
     */
    public void haveNewData(final TFHBridgeMain main){
        handler.post(new Runnable() {
            @Override
            public void run() {
                //TODO main thing
                short command = main.getCommand();
                Log.d("(T)SERVER COMMAND:", String.valueOf(command));

                switch(command){
                    case TFHComm.ROOM_NEW_PLAYER:
                        Log.d("(T)ACTION", "room new player");
                        TFHBridgeDataNewPlayer tfhBridgeDataNewPlayer = (TFHBridgeDataNewPlayer) main.getData();
                        inRoomPlayer = (short) (tfhBridgeDataNewPlayer.getNewPlayerNumber() + 1);
                        Log.d("(T)IN ROOM PLAYER", String.valueOf(inRoomPlayer));
                        changeWaitingPerson();
                        break;
                    case TFHComm.ROOM_DATA:
                        Log.d("(T)ACTION", "room data");
                        TFHBridgeDataRoom tfhBridgeDataRoom = (TFHBridgeDataRoom) main.getData();
                        String dataRoomCommand = tfhBridgeDataRoom.getCommand();
                        if (dataRoomCommand.equalsIgnoreCase("START")){
                            teamScore[0] = tfhBridgeDataRoom.getNorthernHeap();
                            teamScore[1] = tfhBridgeDataRoom.getEeasternHeap();
                            tableCard = new short[4];
                            initPlayer = tfhBridgeDataRoom.getInitialPlayerNumber();
                            nowPlayer = tfhBridgeDataRoom.getNowPlayerNumber();
                            refreshTableGUI();
                        }else{
                            tableCard[nowPlayer] = tfhBridgeDataRoom.getCardId();
                            nowPlayer = tfhBridgeDataRoom.getNowPlayerNumber();
                            refreshTableGUI();
                        }
                        break;
                }

            }
        });
    }

    private void refreshTableGUI() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView tableDisplayNowPlayer = (TextView) findViewById(R.id.table_display_word);
                tableDisplayNowPlayer.setText(getString(R.string.table_game_now_playing, nowPlayer));

                TextView tableTeamScore0 = (TextView) findViewById(R.id.table_game_team_score_0);
                tableTeamScore0.setText(getString(R.string.team_score_0, teamScore[0]));

                TextView tableTeamScore1 = (TextView) findViewById(R.id.table_game_team_score_1);
                tableTeamScore1.setText(getString(R.string.team_score_1, teamScore[1]));


                for (int i=0; i<4; i++){
                    cardImageViewArray[i].setImageResource(Functions.cardToDrawableID(tableCard[i]));
                }

//                if (tableCard[0] != 0) {
//                    ImageView imageView = (ImageView) findViewById(R.id.table_card_0);
//                    imageView.setImageResource(cardToDrawableID(tableCard[0]));
//                }
            }
        });
    }



}