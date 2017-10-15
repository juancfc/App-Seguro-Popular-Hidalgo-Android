package com.repss.apprepss;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Se elimina el registro en FCM
 */
public class UnregisterTags {

    public UnregisterTags(){
    }

    /**
     * Se elimina el registro en FCM
     */
    public void Unregister(){
        String FCM_token = FirebaseInstanceId.getInstance().getToken();
    }
}
