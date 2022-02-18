package com.example.kafkaexample

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.function.Function
import java.util.function.Supplier

@Configuration
class EntryPoint {

    @Bean
    fun producer() : Supplier<Flux<Long>> {
        return Supplier { Flux.interval(Duration.ofMillis(100)).log() }
    }

    @Bean
    fun processor() : Function<Long, String> {
        return Function{ input -> "Number$input" }
    }

}