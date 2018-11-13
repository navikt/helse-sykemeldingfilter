val kafkaVersion = "2.0.1"
val confluentVersion = "5.0.0"
val ktorVersion = "1.0.0-beta-3"
val prometheusVersion = "0.5.0"
val orgJsonVersion = "20180813"
val slf4jVersion = "1.8.0-beta2"

val junitJupiterVersion = "5.3.1"
val spekVersion = "1.2.1"
val kluentVersion = "1.41"

val mainClass = "no.nav.helse.AppKt"

plugins {
   application
   kotlin("jvm") version "1.3.0"
   id("com.github.johnrengelman.shadow") version "2.0.0"
}

buildscript {
   dependencies {
      classpath("org.junit.platform:junit-platform-gradle-plugin:1.2.0")
   }
}

application {
   mainClassName = "$mainClass"
}

dependencies {
   compile(kotlin("stdlib"))
   compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.0")

   compile("org.apache.kafka:kafka-clients:$kafkaVersion")
   compile("org.apache.kafka:kafka-streams:$kafkaVersion")
   compile("io.prometheus:simpleclient_common:$prometheusVersion")
   compile("io.prometheus:simpleclient_hotspot:$prometheusVersion")
   compile("io.ktor:ktor-server-netty:$ktorVersion")
   compile("org.json:json:$orgJsonVersion")
   compile("org.slf4j:slf4j-simple:$slf4jVersion")

   testCompile("org.apache.kafka:kafka-streams-test-utils:$kafkaVersion")
   testCompile("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
   testCompile("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
   testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
   testCompile("org.amshove.kluent:kluent:$kluentVersion")
   testCompile("org.jetbrains.spek:spek-api:$spekVersion") {
      exclude(group = "org.jetbrains.kotlin")
   }
   testRuntime("org.jetbrains.spek:spek-junit-platform-engine:$spekVersion") {
      exclude(group = "org.junit.platform")
      exclude(group = "org.jetbrains.kotlin")
   }
}

repositories {
   jcenter()
   mavenCentral()
   maven("http://packages.confluent.io/maven/")
   maven("https://dl.bintray.com/kotlin/ktor")
}

java {
   sourceCompatibility = JavaVersion.VERSION_1_10
   targetCompatibility = JavaVersion.VERSION_1_10
}

tasks.withType<Test> {
   useJUnitPlatform()
   testLogging {
      events("passed", "skipped", "failed")
   }
}

tasks.withType<Wrapper> {
   gradleVersion = "4.10.2"
}


