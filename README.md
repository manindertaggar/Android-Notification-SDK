### README for Android Notifications SDK

# Android Notifications SDK

**Android Notifications SDK** simplifies the process of rendering notifications in Android apps, removing the need to write repetitive boilerplate code. This SDK supports default Android notification styles and allows for dynamic, server-configured notifications, enabling easier customization and management.

## Features:
- Support for all default notification styles: big text, big picture, inbox, and more.
- Server-configurable settings: title, body, images, small icon, deep links, and more.
- [Upcoming] Remove notifications remotely

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


| Template Type   | Data Jsom                                            | Screenshot                               |
|------------------|--------------------------------------------------------|------------------------------------------|
| Normal           | ```{ "title": "Test Notification", "body": "This is a normal notification.", ...} ``` | <img src="https://github.com/user-attachments/assets/886e798f-0f75-4604-b297-a611e76e3d2f" alt="Normal Screenshot" width="800"/> |
| Big Text         | ``` ``` | <img src="path/to/big_text_screenshot.png" alt="Big Text Screenshot" width="200"/> |
| Conversation      | ``` ``` | <img src="path/to/conversation_screenshot.png" alt="Conversation Screenshot" width="200"/> |
| Large            | ```  ``` | <img src="path/to/large_screenshot.png" alt="Large Screenshot" width="200"/> |
| | ```  ``` | <img src="path/to/large_screenshot.png" alt="Large Screenshot" width="200"/> |

To add buttons to any of the above types

## Contributing:
Contributions are welcome! If you'd like to submit pull requests, report issues, or suggest features, feel free to contribute on GitHub.

---

**Android Notifications SDK** aims to make notification management easy, efficient, and scalable for all Android developers.
