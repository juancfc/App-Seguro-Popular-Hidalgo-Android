package com.repss.apprepss;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by maste on 25/10/2016.
 */
public class UnregisterTags {



    public UnregisterTags(){
    }

    public void Unregister(){
        String FCM_token = FirebaseInstanceId.getInstance().getToken();


    }
}
