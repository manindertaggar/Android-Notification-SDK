### [WORK IN PROGRESS]

### README for Android Notifications SDK

# Android Notifications SDK

**Android Notifications SDK** simplifies the process of rendering notifications in Android apps, removing the need to write repetitive boilerplate code. This SDK supports default Android notification styles and allows for dynamic, server-configured notifications, enabling easier customization and management.

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

### How to trigger Notification

Please follow Firebase's guidelines for delivering the notifications, e.g., using Admin SDK or making an API call.
https://firebase.google.com/docs/cloud-messaging/server

Please make sure for the payload, you do not send the notification, please send all the required fields in the data object.
As per the official documentation, all of the subfields send in the data must be stringified as the data object is only available as `Map<String, String>`



```json
{
  "notification":null,
  "data":{
      "title": "Test Notification",
      "body": "This is a normal notification."
  }
}
```


#### Supported TEmplates

| Template Type   | Data Json                                            | Screenshot                               |
|------------------|--------------------------------------------------------|------------------------------------------|
| Normal           | ```{ "title": "Test Notification", "body": "This is a normal notification.", ...} ``` | <img src="https://github.com/user-attachments/assets/886e798f-0f75-4604-b297-a611e76e3d2f" alt="Normal Screenshot" width="800"/> |
| Big Text         | ``` ``` | <img src="path/to/big_text_screenshot.png" alt="Big Text Screenshot" width="200"/> |
| Conversation      | ``` ``` | <img src="path/to/conversation_screenshot.png" alt="Conversation Screenshot" width="200"/> |
| Large            | ```  ``` | <img src="path/to/large_screenshot.png" alt="Large Screenshot" width="200"/> |
| | ```  ``` | <img src="path/to/large_screenshot.png" alt="Large Screenshot" width="200"/> |

To add buttons to any of the above types


## Features:
- Support for all default notification styles: big text, big picture, inbox, and more.
- Server-configurable settings: title, body, images, small icon, deep links, and more.
- [Upcoming] Remove notifications remotely

## Why Use This SDK?
Currently, Android developers must manually write code for notification rendering and establish custom backend contracts. **Android Notifications SDK** provides a standardized approach that simplifies this process, helping developers save time, minimize errors, and streamline notification handling.

## Contributing:
Contributions are welcome! If you'd like to submit pull requests, report issues, or suggest features, feel free to contribute on GitHub.

---

**Android Notifications SDK** aims to make notification management easy, efficient, and scalable for all Android developers.
