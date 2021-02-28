package org.aspedemo.serverstate;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerStateController {
	
	@GetMapping("/state")
	public HashMap<String, Object> getServerState() {
		HashMap<String, Object> response = new HashMap<>();
		response.put("online", true);
		return response;
	}
}
