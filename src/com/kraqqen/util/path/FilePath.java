package com.kraqqen.util.path;

import java.io.File;

public class FilePath {
	
	File[] roots = File.listRoots();
	
	public void getFilePath(String s){
		for(File root : roots){
			if(root.exists()){
				
			}
		}
	}

}
