# Google Sign-In Setup & Implementation Guide

Google Sign-In is fully integrated into **BusTracking** via Firebase Authentication and `GoogleSignInClient`.

## What Has Been Fixed & Implemented
1. **XML Layout Wiring**: Added `android:id="@+id/btnGoogleSignIn"` to the Google Sign-In button in `activity_login.xml` so that `LoginActivity` can successfully bind and trigger the Google Sign-In flow without throwing a `NullPointerException`.
2. **Flow Handlers**: Both `MainActivity` and `LoginActivity` include `GoogleSignInClient`, `ActivityResultContracts.StartActivityForResult()`, and Firebase credential exchange (`GoogleAuthProvider.getCredential`).
3. **Graceful Error Handling**: If Google Sign-In is tapped before configuring the Web Client ID, a friendly Toast message guides you to update `strings.xml`.

---

## Steps to Make Sign in with Google Fully Functional

To complete the setup and test Google Sign-In on your device/emulator, follow these steps:

### 1. Enable Google Sign-In in Firebase Console
1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Select your project (`pilibus-99b16`).
3. Navigate to **Authentication** > **Sign-in method**.
4. Click **Add new provider** > **Google**.
5. Enable it and specify your project support email. Click **Save**.

### 2. Get your Web Client ID
1. In the Firebase Console, go to **Project settings** (gear icon next to Project Overview).
2. Scroll down to your Android app or Web app config, or go to the **Google Cloud Console** (Credentials page) referenced by your Firebase project.
3. Locate the **Web client ID** (format: `YOUR_PROJECT_NUMBER-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.apps.googleusercontent.com`).

### 3. Update `strings.xml`
Open `app/src/main/res/values/strings.xml` and replace `YOUR_CLIENT_ID_HERE` with your actual Web Client ID:
```xml
<string name="default_web_client_id">YOUR_ACTUAL_WEB_CLIENT_ID.apps.googleusercontent.com</string>
```

### 4. Add SHA-1 Fingerprints (For Debug/Release)
Google Sign-In requires your app's signing certificate SHA-1 fingerprint registered in Firebase:
1. Run `./gradlew signingReport` in terminal or check Android Studio Gradle panel to get your debug SHA-1.
2. Go to Firebase Console > **Project settings** > **Your apps** > Android app > **SHA certificate fingerprints** > **Add fingerprint**.
3. Download the updated `google-services.json` and replace `app/google-services.json` if necessary.

---

## Verification
1. Run `app:assembleDebug` or deploy the app to your emulator/device.
2. Tap **Sign in with Google** from the welcome screen or login screen.
3. Select your Google account.
4. You will be successfully authenticated with Firebase and navigated to `HomeActivity`.
