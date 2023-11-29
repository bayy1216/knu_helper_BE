package com.reditus.knumate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KnumateApplication

fun main(args: Array<String>) {
	runApplication<KnumateApplication>(*args)
}
