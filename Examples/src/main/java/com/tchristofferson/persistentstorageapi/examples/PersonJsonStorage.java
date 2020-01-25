package com.tchristofferson.persistentstorageapi.examples;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tchristofferson.persistentstorageapi.Storage;

import java.io.*;
import java.util.Map;

public class PersonJsonStorage implements Storage<Person, String> {

    private final File file;
    private final Gson gson;

    public PersonJsonStorage(File file, Gson gson) {
        this.file = file;
        this.gson = gson;
    }

    public Person get(String ssn) throws IOException {
        JsonObject object = JsonParser.parseString(readJson()).getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            String social = entry.getKey();

            if (social.equals(ssn)) {
                JsonObject personObj = entry.getValue().getAsJsonObject();
                String firstName = personObj.get("firstName").getAsString();
                String lastName = personObj.get("lastName").getAsString();

                return new Person(social, firstName, lastName);
            }
        }

        return null;
    }

    public void save(Person person) throws IOException {
        JsonObject object = JsonParser.parseString(readJson()).getAsJsonObject();
        object.add(person.getSsn(), gson.toJsonTree(person));
        write(object.toString());
    }

    public void update(String ssn, Person newPerson) throws IOException {
        save(newPerson);
    }

    public void delete(String ssn) throws IOException {
        JsonObject object = JsonParser.parseString(readJson()).getAsJsonObject();
        if (object.remove(ssn) == null)
            return;

        write(object.toString());
    }

    private String readJson() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line).append("\n");
        }

        bufferedReader.close();
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    private void write(String json) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(json);
        bufferedWriter.close();
    }
}
