package com.momy.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.momy.bean.ServerBean;

public class CurrentFile{
	private String path;
//	private String serverRootPath;
	private ServerBean bean;
	
	/*
	 * Real server root path
	 */
	public CurrentFile(String serverRootPath, String serverPort, String serverHost){
//		this.serverRootPath = serverRootPath;
		this.bean = new ServerBean();
		this.bean.setServerHost(serverHost);
		this.bean.setServerPort(serverPort);
		this.bean.setServerRoot(serverRootPath);
	}
	
	/*
	 * Relative file or directory path
	 */
	public void setPath(String path){
		this.path = path;
	}
	
	public String getList(){
		StringBuilder sb = new StringBuilder();
		
		File current_dir = new File(this.path);
		
		if(current_dir.isFile()){
			/*
			 * Return its contents
			 */
			try {
				FileReader fr = new FileReader(current_dir);
				BufferedReader buffer = new BufferedReader(fr);
				
				String line;
				while((line = buffer.readLine()) != null){
					sb.append(line+"\n");
				}
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			File[] file_list = current_dir.listFiles();
			
			if(file_list.length!=0){
				String new_file_path = this.path.replace(this.bean.getServerRoot(), "");
				String type = "";
				/*
				 *  The list should contain
				 *  
				 *  [type][name] [relative path - path name] [host name] [port number]
				 */
				for(File f : file_list){
					if(!f.isHidden()){
						if(f.isDirectory()){
							type = "1";
						}
						if(f.isFile()){
							type = "0";
						}
						sb.append(type+f.getName()+" "+new_file_path+"/"+f.getName()+" "+this.bean.getServerHost()+" "+this.bean.getServerPort()+"\n");
					}
				}
			} else {	// If the file or folder doesn't exist
				sb.append("3");
			}
		}
		
		
		return sb.toString();
	}
}
