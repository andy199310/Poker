package com.weigreen.poker;

import android.util.Log;

import com.weigreen.ncu.tfh.bridge.TFHBridgeMain;
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
public class TFHClientRoomSocket extends Thread implements Serializable {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private final int ROOM_PORT;

    private MainActivity upper;


    private final String TAG = "TFHClientSocket";

    public TFHClientRoomSocket(int port, MainActivity upper){
        this.ROOM_PORT = port;
        this.upper = upper;


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

                upper.haveNewDataRoom(main);

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{

            }

        }
    }
}
