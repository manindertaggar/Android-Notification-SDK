### README for Android Notifications SDK

# Android Notifications SDK

**Android Notifications SDK** simplifies the process of rendering notifications in Android apps, removing the need to write repetitive boilerplate code. This SDK supports default Android notification styles and allows for dynamic, server-configured notifications, enabling easier customization and management.

## Features:
- Support for all default notification styles: big text, big picture, inbox, and more.
- Server-configurable settings: title, body, images, small icon, deep links, and more.
- Advanced features: conversation style, audio, vibrations, notification IDs, and notification removal.
- Unique notification identification and removal for effective notification management.

## Why Use This SDK?
Currently, Android developers must manually write code for notification rendering and establish custom backend contracts. **Android Notifications SDK** provides a standardized approach that simplifies this process, helping developers save time, minimize errors, and streamline notification handling.

## Getting Started

### 1. Installation:
Add the SDK to your project by including the following in your `build.gradle`:

```gradle
implementation 'co.taggar:notifications:1.0.0'
```

TOML (for Gradle version catalogs):
```toml
[dependencies]
android-notifications-sdk = { group = "co.taggar", name = "notifications", version = "1.0.0" }
```

### 2. Initialization:
Intialize in manifest as follows:
//TODO:

### 3. Trigger a Notification:
Configure your backend to send notification details like title, body, and images. Trigger a notification with a simple call:

//TODO:


## Contributing:
Contributions are welcome! If you'd like to submit pull requests, report issues, or suggest features, feel free to contribute on GitHub.

---

**Android Notifications SDK** aims to make notification management easy, efficient, and scalable for all Android developers.
