package com.provodromo.oauth.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.provodromo.oauth.entities.User;

@Component
// name = "user" -> nome do registro no eureka server
@FeignClient(name = "user", path ="/users")
public interface UserFeignClient {

    @GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);
    
}
