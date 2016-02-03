package com.momy.core;

import com.momy.bean.ServerBean;
import com.momy.utils.CurrentFile;

public class Core {
	private ServerBean server;
	private CurrentFile file;
	
	public Core(ServerBean bean){
		this.server = bean;
//		this.file = new CurrentFile(bean.getServerRoot());
		this.file = new CurrentFile(bean.getServerRoot(), bean.getServerPort(), bean.getServerHost());
	}
	
	public String processInput(String input){
		StringBuilder sb = new StringBuilder();
		
		if(input.equals("")){	// List the server root
			file.setPath(server.getServerRoot());
			sb.append("##### Momy Gopher Server - version 0.1 #####\n");
			sb.append(file.getList());
		} else {				// Return the content of a file
			file.setPath(server.getServerRoot()+input);
			sb.append(file.getList());
		}
		
		return sb.toString();
	}
}
