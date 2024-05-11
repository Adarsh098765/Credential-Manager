# Password Manager App

The main goal of this password manager app is to provide a secure and convenient way to store and
manage all your passwords and login credentials. Instead of having to remember countless passwords
or reusing the same password across multiple accounts (which is insecure), this app acts as a
digital vault where you can safely store and access your passwords with ease.

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



