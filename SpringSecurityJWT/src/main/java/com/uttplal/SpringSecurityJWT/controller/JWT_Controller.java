package com.uttplal.SpringSecurityJWT.controller;
import com.uttplal.SpringSecurityJWT.util.JWT_Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping
public class JWT_Controller {


        @Autowired
        private JWT_Unit jwt_unit;


        @PostMapping("/generate-token")
        public String generateToken(@RequestParam String username, @RequestParam String password) {
            if ("admin".equals(username) && "admin".equals(password)) {
                return jwt_unit.generateToken(username);
            } else {
                return "Invalid Credentials!";
            }
        }


        @GetMapping("/contact")
        public String generateToken() {
            return "Contact US at :9199204438";
        }






        @GetMapping("/fund-transfer")
        public String secureApi(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);

                // Validate the token
                if (jwt_unit.validateToken(token)) {
                    return "This is a secured API, token validated!";
                } else {
                    return "Invalid token!";
                }
            } else {
                return "Authorization header is missing or malformed!";
            }
        }

}
