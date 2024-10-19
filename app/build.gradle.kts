plugins {
    // Spring Boot plugin for easy Spring Boot application configuration
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.9.0" // If you're using Kotlin
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.postgresql:postgresql:42.5.0")
    implementation("org.webjars:bootstrap:5.3.2")
    implementation("org.webjars:webjars-locator-core:0.48")
    testImplementation("org.springframework.boot:spring-boot-starter-test")


    // Jakarta dependencies
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("jakarta.transaction:jakarta.transaction-api:2.0.0")

    // Spring Web dependency
    implementation("org.springframework.boot:spring-boot-starter-web") // Eklenmesi gereken kütüphane
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("com.example.eventmanagement.EventManagementApplication") // Update this to match your main application class
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

// Ensure that the run task can read from standard input
tasks.getByName<JavaExec>("run") {
    standardInput = System.`in`
}
