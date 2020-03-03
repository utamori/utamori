import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.31"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")

    	testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    	testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version")
    	// spek requires kotlin-reflect, can be omitted if already in the classpath
    	testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
}

tasks.test {
	useJUnitPlatform {
		includeEngines 'spek2'
	}
	testLogging {
		events("passed", "skipped", "failed")
	}
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "1.8"
}
