package com.kaisikk.java.webflux.repo;

import com.kaisikk.java.webflux.domain.Language;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LanguageRepo extends ReactiveMongoRepository<Language, String> {

    Mono<Language> findByName(String name);

    Mono<Void> deleteByName(String name);

}
