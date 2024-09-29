plugins {
    // Spring Boot plugin for easy Spring Boot application configuration
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.9.0" // If you're using Kotlin
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Spring Boot starter for web applications
    implementation("org.springframework.boot:spring-boot-starter")

    // Spring Boot starter for data access with JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // PostgreSQL database driver
    runtimeOnly("org.postgresql:postgresql:42.5.0") // Ensure you have the correct version

    // Use JUnit Jupiter for testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
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
