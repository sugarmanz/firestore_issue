# Minimal, Complete, Verifiable Example
Issue: After the Android device deconnects and reconnects a couple times, Firestore ceases to update. The snapshot listener stops firing and the `document('doc').get()` method returns stale data. After creating this example, we suspect that the document structure may have something to do with this issue. Please watch the video for more information.

The app follows a simple MVP pattern, using the snapshot listener to update the UI.


### Steps to reproduce
1. Build and open app
2. Open Firestore data editor (https://console.firebase.google.com/u/0/project/firestore-issue/database/firestore/data~2Fdemo~2Fdemo) _Email me for access_
3. Verify correct functionality of listener by modifying `name` field in the editor (should update on the device UI)
4. Turn on airplane mode on the device
5. Modify the `name` field twice
6. Turn off airplane mode and wait for the UI to update
7. Repeat steps 4-6 until it breaks.
Note: Usually this takes one or two times, but I've had it take up to 5 times. You can tell if it breaks if it doesn't update at all or if it only updates to the first changed value. You can always check if its stuck by using the refresh button, which tries to manually retrieve the data.

Update [7/19]: I removed the `telemtry` field from the `demo` document and it changed the behavior slightly. It would take a few more tries to get it to break, but it still broke consistently. I have left that field in the doc because it does seem to have an effect, but it doesn't seem to be the underlying cause.
