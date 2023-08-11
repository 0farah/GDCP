# Contents <!-- omit in toc -->

- [Application Setup](#application-setup)
  - [Prerequisites](#prerequisites)
  - [Configuration](#configuration)
  - [Enable Developer Mode On Your Device](#enable-developer-mode-on-your-device)
  - [Running The App](#run-the-app)

## Application Setup 

The following sections will walk you through the process of setting up your development environment, installing dependencies, and running the app in an android device.

### Prerequisites

You will need to install [yarn](https://yarnpkg.com) if you don't already have it installed. Also, you will need a version of node that is compatible with the version of yarn specified in the `engines` field of [./package.json](./package.json). If you don't have a compatible version of node installed, you can use [nvm](https://github.com/nvm-sh/nvm) to install a compatible version of node.

```sh
npm install -g yarn
nvm install 16.15
```

This project will need to run on an Android device. You can choose to work with [Android Studio](https://developer.android.com/studio).

### Configuration

Start by cloning the repository:

```sh
git clone https://github.com/0farah/GDCP.git application \ &&
cd application
```

Install all the package dependencies by running the following command from the root of the cloned repository:

```sh
yarn install
```

Some packages need to be built (transpiled) before they can be used from the app. Do this with the following command:

```sh
cd packages/legacy \ &&
yarn run prepare
```

### Enable Developer Mode On Your Device
To enable the developer mode on your device you need to follow these steps:

1. Activate Developer Options:

    a. Open the Settings on your Android device.
    b. Scroll down and look for the "About phone" or "About device" option. It might be under the "System" or "About phone" section.
    c. In this section, find the "Version Number" option in the "Software information" section and tap it multiple times until you receive a message indicating that developer options have been enabled.

2. Enable Debugging Mode:
Once the developer options are enabled, follow these steps:

    a. Go back to the main Settings.
    b. Look for the "System" or "Advanced" option, which might contain the developer options.
    c. In the "Developer" section, find and activate the "USB Debugging" option.

3. Connect to the Computer:
Connect your device to your computer using a USB cable.

4. Confirm Authentication:
If prompted to confirm the connection's authenticity from the device, accept this request.


### Running The App

The simplest way to run the app on Android is via Android Studio. Here's how:

1. Open Android Studio.
2. Select `File -> Open` and navigate to the `packages/legacy/app/android` directory. This will load the project into Android Studio.
3. Run the app on a connected device or emulator by selecting one from the list and clicking the green 'Play' button.

If you prefer using the command line interface (CLI), follow these steps:
(Note: The following instructions assume you have Android Studio and the Android SDK installed in your home directory.)

1. List the available devices:

   ```sh
   adb devices -l
   ```

2. Start Metro, the React Native packager:

   ```sh
   cd packages/legacy/app
   yarn start
   ```

5. Allow the device to communicate with Metro on port `tcp:8097`:

   ```sh
   adb reverse tcp:8097 tcp:8097
   ```

6. Finally, run the app:

   ```sh
   cd packages/legacy/app
   yarn run android
   ```

This will launch the app on your selected Android device for development and testing.
