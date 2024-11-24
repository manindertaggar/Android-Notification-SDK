### [WORK IN PROGRESS]

Automatically Render the Notifications on Android from the backend

# Auto Notifications SDK for Android

**Auto Notifications SDK** simplifies rendering notifications in Android apps, removing the need to write repetitive boilerplate code. This SDK supports default Android notification styles and allows for dynamic, server-configured notifications, enabling easier customization and management.


## Getting Started


### 1. Android Implementation:
Add the SDK to your project by including the following in your `build.gradle`:

```gradle
implementation 'co.taggar:notifications:1.0.0'
```

TOML (for Gradle version catalogs):
```toml
[dependencies]
android-notifications-sdk = { group = "co.taggar", name = "notifications", version = "1.0.0" }
```


Add it to your root build.gradle at the end of repositories:
```
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}
```

### 2. Backend Implementation

Please follow Firebase's guidelines for delivering the notifications, e.g., using Admin SDK or making an API call.
https://firebase.google.com/docs/cloud-messaging/server

Please make sure for the payload, you do not send the notification object, please send all the required fields in the data object.
As per the official documentation, all of the subfields sent in the data must be stringified as the data object is only available as `Map<String, String>`

```json
{
  "notification":null,
  "data":{
      "type": "ANDP" 
      "title": "Test Notification",
      "body": "This is a normal notification."
  }
}
```

#### Note: 
The SDK will only process notifications with `type` = `ANDP` and ignore any other type of notification. The other notifications can be accessed by extending a Service to the `AutoNotificationService` and can have custom implementations just like FirebaseNotificationService.


### What to send from Backend:
`AutoNotificationService` can render almost all types of Android notifications out of the box. Please modify one of the following template objects to fit your needs.


#### Supported Templates

| Template Type | Data Json | Screenshot |
|---------------|----------|----------- |
| DEFAULT          | ```{ "type": "DEFAULT", "title": "Test Notification", "message": "This is a test message from Android code. and its big text is not supported and will be trimmed"} ``` | <img src="https://github.com/user-attachments/assets/886e798f-0f75-4604-b297-a611e76e3d2f" alt="Default Screenshot" width="800"/> |
| BIG_TEXT - Use by default for all long text notifications     | ```"{type": "BIG_TEXT", "title": "Test Notification", "message": "This is a test message from Android code. and the long text should be supported here."``` | <img src="https://github.com/user-attachments/assets/7eb0dd33-86b1-4b21-8ddf-4059c3f8b065" alt="BigText Screenshot" width="800"/> |
| LARGE - Can render expandable image       | ```{"type": "LARGE", "title": "Test Notification", "message": "This is a test message from Android code.", "image", "https://picsum.photos/200/300"}``` | <img src="https://github.com/user-attachments/assets/bb15bb9a-8d79-42ea-96f9-d9fa1173f9b7" alt="Large Screenshot" width="800"/> |
| INBOX - Requires additional `lines` key as a stringified array of strings        | ```{"type": "INBOX", "title": "Test Notification",   "message": "This is a test message from Android code.", "lines":  "[\"line 1\",\"line 2\",\"line 3\",\"line 4\"]", "summery_text":"Emails" }``` | Expanded: <img src="https://github.com/user-attachments/assets/22ef99a8-f1f3-4677-89c8-931c1989e669" alt="Inbox Screenshot Expanded" width="800"/> Collapsed: <img src="https://github.com/user-attachments/assets/9aa65f92-6ff0-404e-8d62-f3835da290c7" alt="Inbox Screenshot Collapsed" width="800"/> |
| CONVERSATION - Pass additional `conversation` key as stringified json       | ```{"type": "CONVERSATION", "title": "Test Notification", "conversation": "[{\"text\": \"Hey, how's it going?\", \"timestamp\": 1634567890123, \"sender\": \"Alice\"}, {\"text\": \"I'm good, thanks! What about you?\", \"timestamp\": 1634567890456, \"sender\": \"Bob\"}]"}``` | <img src="https://github.com/user-attachments/assets/23704702-bf35-412c-8680-1c311329d158" alt="Large Screenshot" width="800"/> |
| Buttons - Pass additional `buttons` key to any of above templates      | ```{ ..., "buttons": "[{\"text\": \"Open App\", \"deeplink\": \"app://open\"}, {\"text\": \"Email\", \"deeplink\": \"mailto:ahdev2020@outlook.com\"}]"``` | <img src="https://github.com/user-attachments/assets/305973c6-34df-4032-ae36-2a53dc9a1195" alt="Large Screenshot" width="800"/> |



## Features:
- Support all default notification styles: big text, big picture, inbox, and more.
- Server-configurable settings: title, body, images, small icon, deep links, and more.
- [Upcoming] Remove notifications remotely

## Why Use This SDK?
Android developers must manually write code for notification rendering and establish custom backend contracts. **Android Notifications SDK** provides a standardized approach that simplifies this process, helping developers save time, minimize errors, and streamline notification handling.

## Contributing:
Contributions are welcome! If you'd like to submit pull requests, report issues, or suggest features, feel free to contribute on GitHub.

---

**Auto Notifications SDK** aims to make notification management easy, efficient, and scalable for all Android developers.
