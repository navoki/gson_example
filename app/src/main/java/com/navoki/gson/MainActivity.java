package com.navoki.gson;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simpleJson = findViewById(R.id.simpleJSON);
        Button simpleJSONFieldNamingPolicy = findViewById(R.id.simpleJSONFieldNamingPolicy);
        Button serializeNulls = findViewById(R.id.serializeNulls);
        Button simpleArrayJSON = findViewById(R.id.simpleArrayJSON);
        Button complexJson = findViewById(R.id.complexJson);


        simpleJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleJSON();
            }
        });
        simpleJSONFieldNamingPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleJSONFieldNamingPolicy();
            }
        });
        serializeNulls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleArrayJSON();
            }
        });
        simpleArrayJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serializeNulls();
            }
        });

        complexJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complexJson();
            }
        });

    }

    void simpleJSON() {
        JSONObject finalResponse = null;
        try {
            finalResponse = new JSONObject("{\n" +
                    "  \"name\": \"Shivam\",\n" +
                    "  \"role\": \"Mobile Developer\",\n" +
                    "  \"phone\": \"9999999999\"\n" +
                    "}") {
            };
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson1 = new Gson();


        User user = gson1.fromJson(finalResponse.toString(), User.class);
        Log.e("simpleJSON OUTPUT", user.name + " " + user.role);

        String jsonData = gson1.toJson(user);
        Log.e("simpleJSON OUTPUT1", jsonData);
    }

    void simpleJSONFieldNamingPolicy() {
        JSONObject finalResponse = null;
        try {
            finalResponse = new JSONObject("{\n" +
                    "  \"Name\": \"Shivam\",\n" +
                    "  \"Role\": \"Mobile Developer\",\n" +
                    "  \"Phone\": \"9999999999\"\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson2 = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        User user = gson2.fromJson(finalResponse.toString(), User.class);
        Log.e("simpleJSONFieldNamingPolicy OUTPUT", user.name + " " + user.role);
        String jsonData = gson2.toJson(user);
        Log.e("simpleJSONFieldNamingPolicy OUTPUT1", jsonData);
    }

    void serializeNulls() {
        JSONObject finalResponse = null;
        try {
            finalResponse = new JSONObject("{\n" +
                    "  \"name\": \"Shivam\",\n" +
                    "  \"role\": \"Mobile Developer\",\n" +
                    "  \"phone\": \"9999999999\"\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder()
                .serializeNulls().setPrettyPrinting()
                .create();

        User user = gson.fromJson(finalResponse.toString(), User.class);

        Log.e("simpleJSON OUTPUT", gson.toJson(user));

    }

    void simpleArrayJSON() {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray("[\n" +
                    "  {\n" +
                    "    \"name\": \"Shivam\",\n" +
                    "    \"phone\": \"9999999999\",\n" +
                    "    \"role\": \"Mobile Developer\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"John\",\n" +
                    "    \"phone\": \"9988888888\",\n" +
                    "    \"role\": \"Web Developer\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Ajay\",\n" +
                    "    \"phone\": \"9999999999\",\n" +
                    "    \"role\": \"PHP Developer\"\n" +
                    "  }\n" +
                    "]");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>() {
        }.getType();

        List<User> userList = gson.fromJson(jsonArray.toString(), listType);
        Log.e("simpleArrayJSON OUTPUT", userList.size() + "\n" + userList.get(1).name);

        String jsonData = gson.toJson(userList);
        Log.e("simpleArrayJSON OUTPUT", jsonData);
    }


    void complexJson() {
        //GENDER value 1=MALE, 2=FEMALE
        JSONObject jsonObject1 = null;
        JSONObject jsonObject2 = null;
        try {
            jsonObject1 = new JSONObject("{\n" +
                    "  \"name\": \"Shivam\",\n" +
                    "  \"phone\": \"9999999999\",\n" +
                    "  \"role\": \"Mobile Developer\",\n" +
                    "  \"gender\": 2\n" +
                    "}");

            jsonObject2 = new JSONObject("{\n" +
                    "  \"name\": \"Shivam\",\n" +
                    "  \"phone\": \"9999999999\",\n" +
                    "  \"role\": \"Mobile Developer\",\n" +
                    "  \"gender\": 1}");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        NewUser user1 = gson.fromJson(jsonObject1.toString(), NewUser.class);
        NewUser user2 = gson.fromJson(jsonObject2.toString(), NewUser.class);

        Log.e("Complex json OUTPUT1  :", user1.gender + " " + user2.gender);
        Log.e("Complex json OUTPUT2 :", gson.toJson(user1));
        Log.e("Complex json OUTPUT3  :", gson.toJson(user2));
    }
}
