package com.example.arv_etym;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    TextView t1;
    EditText e1;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.textView);
        e1 = findViewById(R.id.editText2);


        url = dictionaryEntries();


    }

    public void requestApiButtonClick(View v)
    {
        MyDictionaryRequest myDictionaryRequest = new MyDictionaryRequest();
        myDictionaryRequest.execute(url);


    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = "Ace";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id;
    }


}
