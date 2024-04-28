<uses-permission android:name="android.permission.INTERNET"></uses-permission>

public class MainActivity extends AppCompatActivity {
   Button b1,b2;
   EditText e1;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);


       b1=(Button) findViewById(R.id.b1);
       b2=(Button) findViewById(R.id.b2);
       e1=(EditText) findViewById(R.id.e1);


       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),MainActivity2.class);
               i.putExtra("load","default");
               startActivity(i);
           }
       });


       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),MainActivity2.class);
               i.putExtra("load",e1.getText().toString());
               startActivity(i);
           }
       });
   }
}

package com.example.weby;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity2 extends AppCompatActivity {
   WebView w;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2);
       w=(WebView) findViewById(R.id.w1);
       w.setWebViewClient(new WebViewClient());
       Intent i= getIntent();
       String ext=i.getStringExtra("load");
       if(ext.equals("default")){
           w.loadUrl("https://www.google.com");
       }
       else{
           w.loadUrl(ext);
       }
   }
}
