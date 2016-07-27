package com.kraqqen.util.dat_str;

import java.nio.Buffer;

public abstract class DataStream {
	
	public enum AccessMode{
		READ,
		WRITE
	}
	
	private AccessMode accessMode;
	private String name;
	
	public DataStream(AccessMode accessMode){
		this.name = null;
		this.accessMode = accessMode;
	}
	
	public DataStream(String name, AccessMode accessMode){
		this.name = name;
		this.accessMode = accessMode;
	}
	
	public void copy(DataStream other){
		other = this;
	}
	
	abstract long read(Buffer buffer, long count);
	
	abstract long write(Buffer buffer, long count);
	
	abstract void writeString(String string, Encoding stringEncoding);
	
	abstract void writeWString(String string, Encoding stringEncoding); 
	
	abstract String getAsString();
	
	abstract String getAsWString();
	
	abstract void skip(long count);
	
	abstract void seek(long pos);
	
	abstract long tell();
	
	abstract boolean eof();
	
	abstract long size();
	
	abstract void close();
	
	abstract boolean isReadable();
	
	abstract boolean isWritable();
	
	public String getName(){
		return name;
	}
	
	public AccessMode getAccessMode() {
		return accessMode;
	}

}
