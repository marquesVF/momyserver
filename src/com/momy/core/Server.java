package com.momy.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.momy.bean.ServerBean;
import com.momy.utils.Log;

public class Server extends Thread {
	private ServerBean serverBean;
	private Socket socket;

	public Server(ServerBean server, Socket serverSocket){
		this.serverBean = server;
		this.socket = serverSocket;
	}
	
	
	
	public ServerBean getServerBean(){
		return this.serverBean;
	}

	@Override
	public void run() {
		try {
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String input = in.readLine();	
			
			Core core = new Core(serverBean);
			String output = core.processInput(input);
			
			out.println(output);
			
			socket.close();
		} catch (NumberFormatException | IOException e) {
			Log.e("Could not listen on port "+this.serverBean.getServerPort());
			System.exit(-1);
		}
	}
}
