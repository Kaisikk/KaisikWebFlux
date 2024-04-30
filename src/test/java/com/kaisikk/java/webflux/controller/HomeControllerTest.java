package com.kaisikk.java.webflux.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebFluxTest(HomeControllerTest.class)
class HomeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private HomeController homeController;

    @BeforeEach
    public void setUp(){
        this.homeController = new HomeController();
    }

    @Test
    void index() {
        StepVerifier.create(homeController.index())
                .expectNext("Welcome to the Jungle").verifyComplete();
    }

//    @Test
//    public void indexV2(){
//        webTestClient.get().uri("/api/")
//                .accept(MediaType.TEXT_PLAIN)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(String.class)
//                .isEqualTo("Welcome to the Jungle");
//
//    }

}