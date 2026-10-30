BusTracking (Android)

A modern Android application designed for real-time transit tracking, terminal navigation, schedule lookups, and transit notifications.

Table of Contents

Overview

Key Features & Modules

Architecture & Screen Flow

Tech Stack & Dependencies

Project Structure

Prerequisites & Setup

Configuration & Environment Keys

Permissions

Contributing

License

Overview

BusTracking simplifies public transit commuting by enabling users to locate nearby terminals, track real-time bus locations, look up route timetables, bookmark favorite stations, and receive arrival alerts.

Key Features & Modules

1. Account Access & Authentication

User sign-up, sign-in, and password reset flows.

Session persistence and token refresh management.

Guest/anonymous browsing mode for quick lookups.

2. Bus & Terminal Information

Interactive map view tracking active bus units along designated routes.

Detailed terminal listings, platform locations, and operating hours.

Route details showing origin, destination, intermediate stops, and transfer points.

3. Schedules & Timetables

Search and filter schedules by route number, terminal, and time of day.

Estimated Time of Arrival (ETA) and delay detection.

Offline-accessible timetable snapshots for frequent lines.

4. Favorites & Bookmarks

Quick-access pinboard for frequently used bus routes and terminals.

One-tap status check from the main dashboard.

5. Notifications & Alerts

Real-time alerts for impending bus arrivals and route departures.

Service advisories, delays, maintenance notices, and detour alerts via Push Notifications.

6. Profile & Settings Management

User profile updates (name, avatar, contact information).

Notification preference customization (mute specific lines, threshold alarms).

App settings including dark/light theme toggle and measurement units.

Architecture & Screen Flow

The application follows the recommended Android App Architecture (MVVM / Clean Architecture):

[UI Layer (Activities / Fragments / Compose)]
                 |
                 v
       [ViewModel Layer]
                 |
                 v
       [Repository Pattern]
        /                 \
       v                   v
[Local Cache (Room)]   [Remote Data Source (REST / Firebase / WebSocket)]


High-Level Screen Flow

graph TD
    Splash[Splash Screen] --> AuthCheck{Authenticated?}
    AuthCheck -- No --> Auth[Login / Register Screen]
    AuthCheck -- Yes --> Dashboard[Home Dashboard]
    Auth --> Dashboard

    Dashboard --> MapView[Bus & Terminal Tracker]
    Dashboard --> Schedules[Schedule & Timetable Screen]
    Dashboard --> Favorites[Saved Routes & Terminals]
    Dashboard --> Notifications[Notification Center]
    Dashboard --> Profile[Profile & Preferences]

    MapView --> RouteDetail[Route & Stop Details]
    Schedules --> RouteDetail
    Favorites --> MapView


Tech Stack & Dependencies

Platform: Android SDK (minSdk: 24, targetSdk: 34+)

Languages: Kotlin / Java

Architecture: MVVM (Model-View-ViewModel)

UI Toolkit: Android Views (XML / ViewBinding) or Jetpack Compose

Asynchronous Operations: Kotlin Coroutines & Flow / RxJava

Networking: Retrofit2 + OkHttp / Ktor Client

Local Storage: Room Database / DataStore Preferences

Maps & Location: Google Maps SDK / Mapbox SDK, Google Play Services Location API

Push Notifications: Firebase Cloud Messaging (FCM)

Dependency Injection: Dagger Hilt / Koin

Project Structure

app/
 ├── src/
 │    ├── main/
 │    │    ├── AndroidManifest.xml
 │    │    ├── java/ (or kotlin/)
 │    │    │    └── com/kaito/bustracking/
 │    │    │         ├── data/
 │    │    │         │    ├── local/         # Room DB, DAOs, DataStore
 │    │    │         │    ├── remote/        # API interfaces, Retrofit models
 │    │    │         │    └── repository/    # Repository implementations
 │    │    │         ├── di/                 # Dependency injection modules
 │    │    │         ├── ui/
 │    │    │         │    ├── auth/          # Login, Register, Password Recovery
 │    │    │         │    ├── map/           # Bus map tracking & terminal views
 │    │    │         │    ├── schedules/     # Route schedules & timetables
 │    │    │         │    ├── favorites/     # Bookmarked routes/terminals
 │    │    │         │    ├── notifications/ # Notification feed & alerts
 │    │    │         │    └── profile/       # Profile management & settings
 │    │    │         └── utils/              # Helper utilities & extensions
 │    │    └── res/
 │    │         ├── layout/                  # Screen XML layouts
 │    │         ├── navigation/              # NavGraph definitions
 │    │         └── values/                  # Strings, colors, styles
 └── build.gradle.kts


Prerequisites & Setup

Prerequisites

Android Studio Hedgehog (2023.1.1) or later

JDK 17+

Android SDK with Platform tools installed

Installation & Run

Clone the repository:

git clone https://github.com/kaito-jp/bustracking.git
cd bustracking


Open in Android Studio:

Launch Android Studio.

Choose File > Open... and select the cloned directory.

Sync Gradle:

Allow Android Studio to download dependencies and perform project synchronization.

Run the App:

Connect an Android device with USB debugging enabled or launch an emulator.

Click the green Run button or execute:

./gradlew installDebug


Configuration & Environment Keys

To enable real-time map views and backend synchronization, add your credentials:

Create a local.properties file in the root project directory (if not present).

Define required API keys:

MAPS_API_KEY="YOUR_GOOGLE_MAPS_API_KEY"
BACKEND_BASE_URL="https://api.yourdomain.com/v1/"


If using Firebase (Auth / FCM), place google-services.json into the app/ root directory.

Permissions

The app requests the following core Android permissions:

<!-- Network Communication -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- Location Services -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

<!-- Notifications (Android 13+) -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />


Contributing

Fork the Project.

Create your Feature Branch (git checkout -b feature/NewFeature).

Commit your Changes (git commit -m 'Add NewFeature').

Push to the Branch (git push origin feature/NewFeature).

Open a Pull Request.

License

This project is licensed under the MIT License (or specify your preferred license).
