package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

	@RequestMapping("/auth/unauthorized")
	public Map<String, String> unauthorized(HttpServletRequest req, HttpServletResponse res) {
		res.setStatus(401);
		Map<String, String> errorJson = new HashMap<>();
		errorJson.put("error", "Your request lacks the proper authorization");
		
		return errorJson;
	}

}
