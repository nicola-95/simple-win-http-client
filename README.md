# simple-win-http-client

## Introduction

This application is a Kotlin/Native HTTP client using C Interop and libcurl. It bases on the relative JetBrains [tutorial](https://kotlinlang.org/docs/native-app-with-c-and-libcurl.html) to build a Kotlin/Native HTTP client on Windows.

## Getting start

- Clone the repository or download the project as a ZIP file.
- Download the [curl](https://curl.se/windows/) library for Windows (based on your computer processor).
- Download the [cacert.pem](https://curl.se/docs/caextract.html) file. This file is needed to perform the HTTP requests correctly
- Inside `build.gradle.kts` file, change the `includePath` _val_ based on the position of the `include` subfolder of the curl library (to let the application pick curl headers and functions).
- Inside `libcurl.def` file, change the last `linkerOpts` entry based on the position of the `lib` subfolder of the curl library (to let the application pick the `libcurl.a` file).
- Inside `Main.kt` file, change the `certPath` _val_ based on the position of the `cacert.pem` file.
- Build the project. It will generate an exe file per build variant in _build/bin/native_.

## Run the application

To be run correctly, the exe file needs to access the `libcurl.dll` file. Copy that file from the `bin` subfolder of the curl library into the same directory as the exe file.

:warning: Important note: sometimes Windows expects a `libcurl.dll` file with a different name. In my case, I needed to call it `libcurl-4.dll` to make the exe file run with no errors. Pay attention to the error message Windows will display.

Lastly, run the exe from cmd.

For any trouble with building or running the app, feel free to open an issue.
