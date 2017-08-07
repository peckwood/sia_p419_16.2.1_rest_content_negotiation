package rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import rest.entity.User;
import rest.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public List<User> users(@RequestParam(value="max", defaultValue="9223372036854775807") long max, 
			@RequestParam(value="count", defaultValue="20") int count){
		return userRepository.findUsers();
	}
	
	
}
