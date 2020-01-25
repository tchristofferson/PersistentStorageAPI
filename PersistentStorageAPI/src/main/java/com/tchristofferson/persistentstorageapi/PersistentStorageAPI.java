package com.tchristofferson.persistentstorageapi;

import java.util.HashMap;
import java.util.Map;

public class PersistentStorageAPI {

    private final Map<String, Storage> storage;

    public PersistentStorageAPI() {
        this.storage = new HashMap<>();
    }

    /**
     * Register a new {@link Storage} to be used
     * @param storageId The unique storage id. Case sensitive
     * @param storage The storage to be used
     * @return {@code true} if successful, {@code false} if storageId already exists
     */
    public boolean registerStorage(String storageId, Storage storage) {
        if (this.storage.containsKey(storageId))
            return false;

        this.storage.put(storageId, storage);
        return true;
    }

    /**
     * Get a previously registered storage. Use {@link PersistentStorageAPI#registerStorage(String, Storage)} to register
     * @param storageId The unique storage id. Case sensitive
     * @return The storage previously registered with the specified storageId
     */
    public Storage getStorage(String storageId) {
        return storage.get(storageId);
    }

}
