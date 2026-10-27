# Password Vault: Credential Manager for Android

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-7F52FF?logo=kotlin&logoColor=white) ![Jetpack](https://img.shields.io/badge/Jetpack-Compose-4285F4?logo=jetpackcompose&logoColor=white) ![Room](https://img.shields.io/badge/Room-database-4479A1?logo=sqlite&logoColor=white) ![Hilt](https://img.shields.io/badge/Hilt-DI-2496ED) ![Android](https://img.shields.io/badge/Android-minSdk%2026-3DDC84?logo=android&logoColor=white)

A password-manager app built with **Jetpack Compose and Clean Architecture**. It stores website credentials in a local Room database behind a **biometric unlock** (`BiometricPrompt`), and includes a password generator and a password-strength meter.

## Architecture

```
presentation/  CredentialScreen, CredentialDetailBottomSheet, AddCredentialBottomSheet
               CredentialViewModel (state + events)
domain/        use cases: GetCredentials, GetCredential, AddCredential, DeleteCredential
               CredentialRepo (interface)
data/          CredentialRepoImpl ── CredentialDAO ── CredentialDB (Room)
util/          BiometricPromptManager, Encryption, ImeListener
```

**Tech stack:** Kotlin · Jetpack Compose (Material 3) · Room · Hilt · Coroutines & Flow · AndroidX Biometric · MVVM · Clean Architecture

> **Security note:** this is a learning project. Passwords are currently encrypted with AES using an app-embedded key. The planned upgrade is a key generated and held in the **Android Keystore** with **AES-GCM**, unlocked by biometric authentication (`setUserAuthenticationRequired`). Don't store real credentials in it yet.

## Features

Add/Update Credential:

- The AddCredentialBottomSheet composable allows users to add a new credential or update an existing one.
- Users can input the website, username, and password for the credential.

- If an existing credential is passed to the composable, the fields will be pre-populated with the current values,allowing for easy updating.

Password Visibility Toggle:

- Users can toggle the visibility of the password field by clicking the eye icon in the trailing icon
  of the password TextField.

- When the password is visible, the icon shows an "eye" symbol;when it'shidden, the icon shows a "crossed-eye" symbol.

Password Generation:

- The composable includes a password generator feature, accessible through an icon in the trailing icon of the password TextField.
- Clicking the password generator icon will toggle the visibility of a "Generate" button.
- When the "Generate" button is visible, clicking it will generate a strong,random password and set it as the value of the password TextField.
- The generated password is 12 characters long and includes lowercase letters, uppercase letters, digits, and special characters.

Copy Password to Clipboard:

- Users can easily copy the current password to the system clipboard by clicking the copy icon in the trailing icon of the password TextField.
- This functionality is only enabled when the password field
  is not empty.

Password Strength Indicator:

- The composable provides a visual indication of the password strength based on the current value in the password field.
- The password strength is categorized into four levels: Weak, Fair, Good, and Strong.
- The strength level is displayed as text and also represented by a colored circle (red for Weak, yellow for Fair, olive green for Good, and green for Strong).
- The password strength is evaluated based on the length of the password and the presence of different character types (digits,
  lowercase letters, uppercase letters, and special characters).

## Screenshots


![Dashboard](https://github.com/Adarsh098765/Credential-Manager/assets/43262684/8cd4fe3d-af20-42b7-bad4-80f2df6e6239)
![ShowCredential](https://github.com/Adarsh098765/Credential-Manager/assets/43262684/eed43987-9d8d-45c2-ab55-2f0ccc4aa873)
![Add Credential](https://github.com/Adarsh098765/Credential-Manager/assets/43262684/625fcf80-9159-4c59-9a26-95e86d44001c)
![Update Credential](https://github.com/Adarsh098765/Credential-Manager/assets/43262684/b8fd7269-8936-4e4b-a468-8bb72d8dc74d)

---

Built by **[Adarsh Thakur](https://adarsh098765.github.io/adarshthakur.github.io/)**, an Android engineer working with Kotlin and Jetpack Compose · [LinkedIn](https://www.linkedin.com/in/adarsh-thakur-b31281171/)
