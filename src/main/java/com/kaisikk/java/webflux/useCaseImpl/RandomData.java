package com.kaisikk.java.webflux.useCaseImpl;

import com.kaisikk.java.webflux.domain.UserDto;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Component
@Getter
public class RandomData {

    List<UserDto> userDtoList;

    @PostConstruct
    private void randomData(){
        userDtoList = new ArrayList<>();
        LongStream.range(1, 100)
                .forEach(x -> userDtoList.add(new UserDto(x, x, "title", "body")));
    }

}
