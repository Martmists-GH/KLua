import com.strumenta.antlrkotlin.gradle.AntlrKotlinTask
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile

plugins {
    kotlin("multiplatform") version "2.4.0"
    id("com.strumenta.antlr-kotlin") version "1.0.10"
}

group = "com.martmists"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js {
        browser()
        nodejs()
    }
    wasmJs {
        browser()
        nodejs()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("com.strumenta:antlr-kotlin-runtime:1.0.10")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.8.0")
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

tasks {
    val generateGrammarSource = register("generateGrammarSource", AntlrKotlinTask::class) {
        description = ""
        enabled = true
        source = fileTree(layout.projectDirectory.dir("src/commonMain/antlr")) {
            include("**/*.g4")
        }
        packageName = "com.martmists.klua.parsing"
        // TODO: Change to build directory?
        outputDirectory = layout.projectDirectory.dir("src/commonMain/kotlin/com/martmists/klua/parsing").asFile
        arguments = listOf(
            "-encoding", "UTF-8",
        )
    }

    withType<AbstractKotlinCompile<*>>().configureEach {
        dependsOn(generateGrammarSource)

        compilerOptions.freeCompilerArgs = listOf(
            "-Xcontext-parameters",
            "-Xexplicit-backing-fields",
        )
    }
}
