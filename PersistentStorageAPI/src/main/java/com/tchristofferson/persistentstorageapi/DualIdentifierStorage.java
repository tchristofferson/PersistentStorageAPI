package com.tchristofferson.persistentstorageapi;

public interface DualIdentifierStorage<T, I1, I2> extends Savable<T> {

    /**
     * Retrieve an object from storage
     * @param identifier1 The first of 2 unique identifiers to identify the object in storage
     * @param identifier2 The second of 2 unique identifiers to identify the object in storage
     * @return Return the object with the specified identifiers, or {@code null} if not found
     */
    T get(I1 identifier1, I2 identifier2);

    /**
     * Update a pre-existing object in storage
     * @param identifier1 The first of 2 unique identifiers to identify the object in storage
     * @param identifier2 The second of 2 unique identifiers to identify the object in storage
     * @param newObj The new object to replace the old object
     * @throws Exception if there was an error
     */
    void update(I1 identifier1, I2 identifier2, T newObj) throws Exception;

    /**
     * Delete the specified object
     * @param identifier1 The first of 2 unique identifiers to identify the object in storage
     * @param identifier2 The second of 2 unique identifiers to identify the object in storage
     */
    void delete(I1 identifier1, I2 identifier2);

}
