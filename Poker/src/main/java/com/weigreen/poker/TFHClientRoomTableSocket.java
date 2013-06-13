package com.weigreen.poker;

import android.util.Log;

import com.weigreen.ncu.tfh.bridge.TFHBridgeDataCard;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataGodCard;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataNewPlayer;
import com.weigreen.ncu.tfh.bridge.TFHBridgeDataPlayer;
import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;
import com.weigreen.ncu.tfh.communication.TFHComm;
import com.weigreen.ncu.tfh.config.TFHConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Green on 2013/6/10.
 */
public class TFHClientRoomTableSocket extends Thread implements Serializable {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private final int ROOM_PORT;

    private TableActivity upper;

    private final Long USER_ID;


    private final String TAG = "TFHClientSocket";

    public TFHClientRoomTableSocket(int port, TableActivity upper){
        this.ROOM_PORT = port;
        this.upper = upper;
        USER_ID = Long.parseLong("0");

    }


    @Override
    public void run(){
        try {
            socket = new Socket(TFHConfig.MAIN_SERVER_IP, this.ROOM_PORT);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                TFHBridgeMain main = (TFHBridgeMain) input.readObject();
                short command =  main.getCommand();
                Log.d(TAG, "(R)Server command:" + command);

                upper.haveNewData(main);

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{

            }

        }
    }


    public boolean dealCard()
    {
        TFHBridgeDataCard data = new TFHBridgeDataCard(null);
        TFHBridgeMain main = new TFHBridgeMain(TFHComm.CARD_DATA, data);


        try {
            output.writeObject(main);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean playerCallGodCard(String command, short playerNumber, short godCardSuit, short heap)
    {
        TFHBridgeDataGodCard data = new TFHBridgeDataGodCard(command, playerNumber, godCardSuit, heap);
        TFHBridgeMain main = new TFHBridgeMain(TFHComm.GOD_CARD_DATA, data);


        try {
            output.writeObject(main);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean playerSendCard(short playerNumber, short cardId)
    {
        TFHBridgeDataPlayer data = new TFHBridgeDataPlayer(playerNumber, cardId);
        TFHBridgeMain main = new TFHBridgeMain(TFHComm.PLAYER_DATA, data);


        try {
            output.writeObject(main);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean playerNewEnter()
    {
        TFHBridgeDataNewPlayer data = new TFHBridgeDataNewPlayer((short)-1);
        TFHBridgeMain main = new TFHBridgeMain(TFHComm.ROOM_NEW_PLAYER, data);


        try {
            output.writeObject(main);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
