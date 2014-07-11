package com.zy.binder.aidl;

interface IMusicPlayService {
	boolean start(String filePath);
	void stop();
	
}
