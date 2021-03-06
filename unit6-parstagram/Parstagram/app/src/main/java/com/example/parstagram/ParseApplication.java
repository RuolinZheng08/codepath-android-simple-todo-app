package com.example.parstagram;

import android.app.Application;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    public static final String APP_TAG = ParseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kG3wvDFqItTcXsP5UIOMGJqZILw6aFq76luF97aH")
                .clientKey("0PVyKvRRaQjUF248SYKj7UFjDBiV0ioxjj6yoBHG")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
