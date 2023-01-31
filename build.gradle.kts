plugins {
    kotlin("multiplatform") version "1.7.10"
}

group = "me.nicola"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        compilations.getByName("main") {
            cinterops {
                val libcurl by creating {
                    val includePath = "C:\\Users\\Nicola\\Documents\\curl-7.87.0_2-win64-mingw\\curl-7.87.0_2-win64-mingw\\include"

                    defFile(project.file("src/nativeInterop/cinterop/libcurl.def"))
                    packageName("libcurl")
                    compilerOpts("-I/$includePath")
                    includeDirs.allHeaders(includePath)
                }
            }
        }
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
}
