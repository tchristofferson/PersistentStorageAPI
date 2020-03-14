package com.tchristofferson.persistentstorageapi;

/**
 * Represents a persistent place to sore objects
 * @param <T> represents the type of object this Savable can store
 */
public interface Savable<T> {

    /**
     * Save specified object to storage
     * @param obj The object to save
     * @throws Exception if there was an error
     */
    void save(T obj) throws Exception;

}
