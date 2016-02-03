package com.momy.core;

import java.io.IOException;
import java.net.ServerSocket;

import com.momy.bean.ServerBean;
import com.momy.utils.Log;

public class RequestHandler {
	private ServerBean serverBean;
	private ServerSocket serverSocket;
	private boolean listening;
	
	public RequestHandler(ServerBean serverBean){
		this.serverBean = serverBean;
		this.listening = true;
	}
	
	public void setState(boolean newState){
		this.listening = newState;
	}
	
	public void onListening(){
		try {
			serverSocket = new ServerSocket(Integer.parseInt(this.serverBean.getServerPort()));
			Log.m("Server listening on port "+this.serverBean.getServerPort());
			while(this.listening){
				new Server(serverBean, serverSocket.accept()).start();
			}
			serverSocket.close();
		} catch (IOException | NumberFormatException e) {
			Log.e("Could not listen on port "+this.serverBean.getServerPort());
			System.exit(-1);
		}
		
	}
}

