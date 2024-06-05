# StudyPal

[![Create APK artifact](https://github.com/drp08/StudyPal/actions/workflows/build-artifact.yaml/badge.svg)](https://github.com/drp08/StudyPal/actions/workflows/build-artifact.yaml)

## Repository Rules

- You **cannot** directly push any commits to main. 
- Pull requests from feature branches are accepted on the conditions that:
  - [build-test](.github/workflows/build-test.yaml) passes.
  - At least one approval from any of the [Authors](#authors)
- Make sure every issue is linked to a Trello card via the GitHub Power-up.
- Every Pull Request and Commit must follow the following naming convection:
  - `<type>: <message>` where the commit message is in lowercase and the `type` may be:
    - `feat`
    - `fix`
    - `style`
    - `refactor`
    - `debug`
    - or anything you deem appropriate.
- Once a PR is approved and passes all checks, make sure you "Squash and Merge" into main.

## Project Structure

* `/composeApp` is for code **for the UI** that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

## Authors

- [@cybercoder-naj](https://github.com/cybercoder-naj)
- [@Harini-Sritharar](https://github.com/Harini-Sritharar)
- [@Virgina-Watson](https://github.com/Virginia-Watson)
- [@Arabella-Hurrell](https://github.com/arabellahurrell)
  
