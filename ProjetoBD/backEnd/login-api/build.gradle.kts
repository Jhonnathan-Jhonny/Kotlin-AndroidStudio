import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.24"
    id("io.ktor.plugin") version "2.3.10"
}

group = "br.com.jhonnathan"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.tomcat.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.0.0")
    implementation("io.ktor:ktor-server-swagger-jvm:2.0.0")
    implementation("io.ktor:ktor-server-sessions:2.0.0")
    implementation("io.ktor:ktor-server-auth:2.0.0")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.0.0")
    implementation("io.ktor:ktor-serialization-gson-jvm:2.0.0")
    implementation("io.ktor:ktor-server-tomcat-jvm:2.0.0")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // MongoDB
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.10.1")

    // Koin Dependency Injection
    implementation("io.insert-koin:koin-ktor:3.5.3")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.3")

    // Bcrypt
    implementation("org.mindrot:jbcrypt:0.4")

    // Test dependencies
    testImplementation("io.ktor:ktor-server-tests-jvm:2.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //Logger
    implementation("io.github.microutils:kotlin-logging:2.0.11")
    implementation("ch.qos.logback:logback-classic:1.2.3")

}

