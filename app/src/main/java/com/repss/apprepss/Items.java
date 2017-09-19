package com.repss.apprepss;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by maste on 08/03/2017.
 */

public class Items implements ClusterItem {
    private final LatLng mPosition;

    public Items(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
