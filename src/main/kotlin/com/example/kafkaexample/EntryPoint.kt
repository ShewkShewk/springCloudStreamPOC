package com.example.kafkaexample

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier

@Configuration
class EntryPoint {

    val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun producer() : Supplier<Flux<Long>> {
        return Supplier { Flux.interval(Duration.ofMillis(100)) }
    }

    @Bean
    fun processor() : Function<Long, String> {
        return Function{ input -> logger.info("Thread ${Thread.currentThread().id} Received: $input, sending out: Number$input"); "Number$input" }
    }

    @Bean
    fun consumer(): Consumer<String> {
        return Consumer{ input: String -> logger.info("Thread: ${Thread.currentThread().id} Received message: $input.")}
    }

}