package com.jongla.realmtest.util;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.SyncConfiguration;

/**
 * Created by mikesolomon on 14/10/16.
 */

public class RealmManager {
    final private static Map<String, Realm> realmMap = new HashMap<>();
    static public Realm getInstance(SyncConfiguration configuration) {
        if (!realmMap.containsKey(configuration.getServerUrl().toString())) {
            realmMap.put(configuration.getServerUrl().toString(), Realm.getInstance(configuration));
        }
        return realmMap.get(configuration.getServerUrl().toString());
    }
}
