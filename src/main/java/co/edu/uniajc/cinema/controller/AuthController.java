package co.edu.uniajc.cinema.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.cinema.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "Authentication", tags = "Authentication")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService Auth_serv;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 404, message = "404 error"),
	        @ApiResponse(code = 500, message = "service error")
	    })
	@PostMapping("/token")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
       String token= Auth_serv.login(username,password);
       if(StringUtils.isEmpty(token)){
           return "no token found!";
       }
       return token;
    }

}
