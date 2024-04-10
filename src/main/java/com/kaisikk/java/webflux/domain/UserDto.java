package com.kaisikk.java.webflux.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    Long userId;

    Long id;

    String title;

    String body;



}
