package com.navoki.gson;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class GenderDataConverter implements JsonDeserializer<Gender>, JsonSerializer<Gender> {

    @Override
    public Gender deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.e("deserialize", json + "");
        try {
            if (json.getAsInt() == 1) {
                return Gender.MALE;

            } else {
                return Gender.FEMALE;
            }
        } catch (JsonSyntaxException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public JsonElement serialize(Gender gender, Type typeOfSrc, JsonSerializationContext context) {
        Log.e("serialize", gender + "");
        if (gender == Gender.MALE) {
            return new JsonPrimitive(1);
        } else {
            return new JsonPrimitive(2);
        }
    }
}
