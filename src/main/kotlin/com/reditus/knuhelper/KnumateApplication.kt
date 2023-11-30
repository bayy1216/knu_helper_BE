package com.reditus.knuhelper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class KnumateApplication

fun main(args: Array<String>) {
	runApplication<KnumateApplication>(*args)
}
