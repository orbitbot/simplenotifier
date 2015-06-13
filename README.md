# simplenotifier
POC CLI and Android client for unidirectional GCM messages

Related blog post: http://pad.dy.fi/sending-messages-to-android-devices-with-gcm/

<br />
to use
------

##### Enable GCM for your (new) app:
- browse to `https://developers.google.com/mobile/add` and follow the instructions
- select Android app
- you may need to change the android package name in the source code to whatever you register in the wizard
- at the final step, copy the server API key and sender ID somewhere

##### android:
- clone repository, import into android studio
- fill in your GCM senderId in `../res/values/strings.xml` under `senderId`
- install application onto Android device (that has Google Play Services installed)
- start application and press "Get token" button
- c/p token (from Logcat)

##### python
- paste server API key and android token into `gcm_sender/config.ini`
- install package in development mode (this might need to be run as root, depending on your setup)
```bash
$ python gcm_sender/setup.py develop 
``` 

<br />
After all the setup, you should be able to run the command provided by the installed python script
```bash
$ gcm-ping
``` 

If everything went correctly, you should receive a notification on your Android device that will open a new activity with the message content when clicked.
