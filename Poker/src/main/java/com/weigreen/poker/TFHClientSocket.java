package com.weigreen.poker;

import android.util.Log;

import com.weigreen.ncu.tfh.bridge.TFHBridgeDataMakeRoom;
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
public class TFHClientSocket extends Thread implements Serializable {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    //The upper class
    private MainActivity upper;

    private final String TAG = "TFHClientSocket";

    private final Long USER_ID;

    public TFHClientSocket(MainActivity upper22){
        this.upper = upper22;
        USER_ID = Long.getLong("0");
    }


    @Override
    public void run(){

        try {
            socket = new Socket(TFHConfig.MAIN_SERVER_IP, TFHConfig.MAIN_SERVER_PORT);
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());

//            output.flush();

        } catch (UnknownHostException e) {
            Log.d(TAG, e.toString());
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        }


        try {
            while(true){
                TFHBridgeMain main = (TFHBridgeMain) input.readObject();
                short command =  main.getCommand();
                Log.d(TAG, "(M)Server command:" + command);

                upper.haveNewData(main);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{


        }
    }

    public boolean makeRoom(String roomName){
        TFHBridgeDataMakeRoom data = new TFHBridgeDataMakeRoom(roomName, 0);
        TFHBridgeMain main = new TFHBridgeMain(TFHComm.MAKE_ROOM, USER_ID, data);
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
