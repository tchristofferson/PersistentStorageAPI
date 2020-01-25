package com.tchristofferson.persistentstorageapi;

/**
 * Represents a means of storage such as a file or a database
 * T represents the type of object this Storage is for
 * I Represents the type of object used as a unique identifier for object T
 */
public interface Storage<T, I> {

    /**
     * Retrieve an object from storage
     * @param identifier A way to uniquely identify the object in storage. Ex. identifier=SSN for a person class because it uniquely identifies a person.
     * @return Return the object with the specified identifier, or {@code null} if not found
     */
    T get(I identifier) throws Exception;

    /**
     * Save specified object to storage
     * @param obj The object to save
     * @throws Exception if there was an error
     */
    void save(T obj) throws Exception;

    /**
     * Update a pre-existing object in storage
     * @param identifier The object to uniquely identify the object to update
     * @param newObj The new object to replace the old object
     * @throws Exception if there was an error
     */
    void update(I identifier, T newObj) throws Exception;

    /**
     * Delete the specified object
     * @param identifier The object to uniquely identify the object to delete
     * @throws Exception if there was an error
     */
    void delete(I identifier) throws Exception;

}
