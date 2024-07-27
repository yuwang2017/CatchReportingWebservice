package com.example.appengine.demos.springboot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.appengine.demos.springboot.service.CloudFileService;

@RestController
public class AudioFileUploadController {

	@Autowired
	CloudFileService cloudService;

	@RequestMapping(value = "/Permit-Mobile/rest/report", method = RequestMethod.POST)
	@ResponseBody
	public SubmitConfirmation upload(@RequestParam("file") MultipartFile file,
			@RequestParam("firearmKey") String firearmKey) throws Exception {
		SubmitConfirmation confirm = new SubmitConfirmation();
		confirm.setStatus("Success");
		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			cloudService.saveAudioFile(bytes, firearmKey);			
		} catch (Exception e) {
			e.printStackTrace();
			confirm.setStatus("Fail");
		}
		return confirm;
	}
	
	@RequestMapping(value = "/getAudio", method = RequestMethod.GET)
	public void getAudioBinary(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fileName = request.getParameter("filename");
		byte[] content = cloudService.retrieveFile(fileName);		
		
		if(content == null) {
			response.setStatus(404);
		} else {
			response.setStatus(200);
			response.setContentType("audio/mpeg");
			response.setContentLength(content.length);
			response.getOutputStream().write(content);
		}
		response.getOutputStream().close();
	}
}
