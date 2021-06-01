import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10" // Can be downgraded
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.mockk", "mockk", "1.11.0")
    testImplementation("io.kotest", "kotest-runner-junit5-jvm", "4.6.0")
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "6.8"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "15"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
