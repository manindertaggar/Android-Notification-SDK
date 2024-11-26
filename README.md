
[![Release](https://jitpack.io/v/co.taggar/android-notifications.svg)](https://jitpack.io/#co.taggar/android-notifications)

# Auto Notifications SDK for Android
**Auto Notifications SDK** simplifies rendering notifications in Android apps, removing the need to write repetitive boilerplate code. This SDK supports default Android notification styles and allows for dynamic, server-configured notifications, enabling easier customization and management.

## Why Use This SDK?
Android developers must manually write code for notification rendering and establish custom backend contracts. **Android Notifications SDK** provides a standardized approach that simplifies this process, helping developers save time, minimize errors, and streamline notification handling. 


## Getting Started

### 1. Android Implementation:
Add the SDK to your project by including the following in your `build.gradle`:

```gradle
dependencies {
  implementation 'co.taggar:android-notifications:1.0.2'
}
```

TOML (for Gradle version catalogs):
```toml
[dependencies]
android-notifications-sdk = { group = "co.taggar", name = "android-notifications", version = "1.0.2" }
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

Please request the notification permissions `POST_NOTIFICATIONS` as per your needs in newer Android versions.
https://developer.android.com/develop/ui/views/notifications/notification-permission


### 2. Backend Implementation

Please follow Firebase's guidelines for delivering the notifications, e.g., using Admin SDK or making an API call.
https://firebase.google.com/docs/cloud-messaging/server

#### Make sure for the payload, you do not send the notification object, please send all the required fields in the data object.

As per the official documentation, all of the subfields sent in the data must be stringified as the data object is only available as `Map<String, String>`

```json
{
  "notification": null,
  "data":{
      "type": "ANDP" 
      "title": "Test Notification",
      "body": "This is a normal notification."
  }
}
```

#### Note: 
The SDK will only process notifications with `type` = `ANDP` and ignore any other type of notification. The other notifications can be accessed by extending a Service to the `AutoNotificationService` and having custom implementations like FirebaseNotificationService.


### What to send from Backend:
`AutoNotificationService` can render almost all Android notifications out of the box. Please modify one of the following template objects to fit your needs.


#### Supported Templates

| Template Type | Data Json | Screenshot |
|---------------|----------|----------- |
| DEFAULT          | ``` { "template": "DEFAULT", "title": "Test Notification", "message": "This is a test message from Android code. and its big text is not supported and will be trimmed"} ``` | <img src="https://github.com/user-attachments/assets/886e798f-0f75-4604-b297-a611e76e3d2f" alt="Default Screenshot" width="800"/> |
| BIG_TEXT - Use by default for all long text notifications     | ``` { "template": "BIG_TEXT", "title": "Test Notification", "message": "This is a test message from Android code. and the long text should be supported here."``` | <img src="https://github.com/user-attachments/assets/7eb0dd33-86b1-4b21-8ddf-4059c3f8b065" alt="BigText Screenshot" width="800"/> |
| LARGE - Can render expandable image       | ```{ "template": "LARGE", "title": "Test Notification", "message": "This is a test message from Android code.", "image", "https://picsum.photos/200/300"}``` | <img src="https://github.com/user-attachments/assets/bb15bb9a-8d79-42ea-96f9-d9fa1173f9b7" alt="Large Screenshot" width="800"/> |
| INBOX - Requires additional `lines` key as a stringified array of strings        | ```{ "template": "INBOX", "title": "Test Notification",   "message": "This is a test message from Android code.", "lines":  "[\"line 1\",\"line 2\",\"line 3\",\"line 4\"]", "summery_text":"Emails" }``` | Expanded: <img src="https://github.com/user-attachments/assets/22ef99a8-f1f3-4677-89c8-931c1989e669" alt="Inbox Screenshot Expanded" width="800"/> Collapsed: <img src="https://github.com/user-attachments/assets/9aa65f92-6ff0-404e-8d62-f3835da290c7" alt="Inbox Screenshot Collapsed" width="800"/> |
| CONVERSATION - Pass additional `conversation` key as stringified json       | ```{ "template": "CONVERSATION", "title": "Test Notification", "conversation": "[{\"text\": \"Hey, how's it going?\", \"timestamp\": 1634567890123, \"sender\": \"Alice\"}, {\"text\": \"I'm good, thanks! What about you?\", \"timestamp\": 1634567890456, \"sender\": \"Bob\"}]"}``` | <img src="https://github.com/user-attachments/assets/23704702-bf35-412c-8680-1c311329d158" alt="Large Screenshot" width="800"/> |
| Buttons - Pass additional `buttons` key to any of above templates      | ```{ ..., "buttons": "[{\"text\": \"Open App\", \"deeplink\": \"app://open\"}, {\"text\": \"Email\", \"deeplink\": \"mailto:ahdev2020@outlook.com\"}]"``` | <img src="https://github.com/user-attachments/assets/305973c6-34df-4032-ae36-2a53dc9a1195" alt="Large Screenshot" width="800"/> |


### Upcoming Features:
- [Upcoming] Remove already sent notifications remotely from user's devices
- [Upcoming] Get callbacks notification clicks, and button clicks directly in the Kotlin listeners, rather than deep links
- [Upcoming] Notification Analytic callbacks e.g. Notification Received, Shown, Opened, Dismissed, etc

## Contributing:
Contributions are welcome! Feel free to contribute on GitHub if you'd like to submit pull requests, report issues, or suggest features.
