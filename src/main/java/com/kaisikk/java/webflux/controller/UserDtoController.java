package com.kaisikk.java.webflux.controller;

import com.kaisikk.java.webflux.domain.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/external", produces = "application/json")
public class UserDtoController {

    private WebClient webClient;

    @PostConstruct
    private void setUpWebClient(){
        webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }

    @GetMapping("/{id}")
    public Mono<UserDto> getById(@PathVariable("id") String id){
        return webClient
                .get()
                .uri("/posts/{id}", id)
                // работает с телом
                .retrieve()
                .bodyToMono(UserDto.class);
    }

}
