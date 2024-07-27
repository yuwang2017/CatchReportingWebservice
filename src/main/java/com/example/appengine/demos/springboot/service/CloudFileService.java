package com.example.appengine.demos.springboot.service;

public interface CloudFileService {
	
	public void saveAudioFile(byte[] audioData, String fileName);
	
	public byte[] retrieveFile(String fileName);
}
