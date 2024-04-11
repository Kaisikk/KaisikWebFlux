package com.kaisikk.java.webflux.controller;

import com.kaisikk.java.webflux.domain.UserDto;
import com.kaisikk.java.webflux.useCaseImpl.RandomData;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

@RestController
@RequestMapping(value = "/external", produces = "application/json")
public class UserDtoController {

    private WebClient webClient;

    @Autowired
    private RandomData randomData;

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

     @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserDto> streaList(){
        return Flux.fromIterable(randomData.getUserDtoList()).delayElements(Duration.ofSeconds(2));
     }

     @GetMapping("/{id}")
    public String getUserDto(@PathVariable("id") Long id, Model model){
        model.addAttribute("dto", Mono.just(randomData.getUserDtoList().get((int) (id -1) )));
        return "dto";
     }

}
