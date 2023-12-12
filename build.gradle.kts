import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    antlr("org.antlr:antlr4:4.13.1")

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
    // Only enable when you need to regenerate the parser/lexer
    generateGrammarSource {
        enabled = false
        arguments = listOf(
            "-package", "com.martmists.klua.parsing",
            "-encoding", "UTF-8",
        )
    }
    generateTestGrammarSource {
        enabled = false
    }

    withType<KotlinCompile> {
        dependsOn(generateGrammarSource)
    }
}
