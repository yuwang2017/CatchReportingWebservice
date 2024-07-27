package com.example.appengine.demos.springboot.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class CloudFileServiceImpl implements CloudFileService {
	
	public static final String BUCKET_NAME = "survey_cat_audio_files";
	
	
	private static Bucket bucket = null;

	
	static {
		Storage storage = StorageOptions.getDefaultInstance().getService();
		// Creates the new bucket
		bucket = storage.get(BUCKET_NAME);
	}
	
	@Override
	public void saveAudioFile(byte[] audioData, String fileName) {
		bucket.create(fileName, audioData);
	}

	@Override
	public byte[] retrieveFile(String fileName) {
		Blob data = bucket.get(fileName);
		if (data != null) {
			return data.getContent();
		}
		return null;
	}
}
