package com.example.arv_etym;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private EditText e1;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.textView);
        e1 = findViewById(R.id.editText2);
        url = dictionaryEntries();
    }
   /* public void requestApiButtonClick(View v)
    {
        MyDictionaryRequest myDictionaryRequest = new MyDictionaryRequest();
        myDictionaryRequest.execute(url);
    } Not necessary (Add finally)!!
    */

    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = e1.getText().toString();
        final String fields = "etymologies";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }


    public void sendRequestOnClick(View v)
{
    MyDictionaryRequest dR = new MyDictionaryRequest(this,t1);
    url = dictionaryEntries();
    dR.execute(url);

}

}
