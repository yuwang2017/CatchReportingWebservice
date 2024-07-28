package com.example.appengine.demos.springboot.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component

public class CloudFileService {

	public static final String BUCKET_NAME = "ert_report_files";


	private static Bucket bucket = null;


	static {
		Storage storage = StorageOptions.getDefaultInstance().getService();
		// Creates the new bucket
		bucket = storage.get(BUCKET_NAME);
	}


	public void saveAudioFile(byte[] audioData, String fileName) {
		bucket.create(fileName, audioData);
	}


	public byte[] retrieveFile(String fileName) {
		Blob data = bucket.get(fileName);
		if (data != null) {
			return data.getContent();
		}
		return null;
	}
}
