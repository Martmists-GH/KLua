plugins {
    kotlin("jvm") version "1.9.21"
    antlr
}

group = "com.martmists"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.9.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    testImplementation(kotlin("test", "1.9.21"))
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf(
            "-Xcontext-receivers"
        )
    }
}

tasks {
    generateGrammarSource {
        enabled = false
    }
    generateTestGrammarSource {
        enabled = false
    }
}
