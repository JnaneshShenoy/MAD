package com.example.tesing;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
   ConstraintLayout c1;
   TextView t;
   ProgressBar level;
   BroadcastReceiver b;




   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);




       c1 = findViewById(R.id.c1);
       level = findViewById(R.id.level);
       t = findViewById(R.id.t);




       b = new BroadcastReceiver() {
           @Override
           public void onReceive(Context context, Intent intent) {
               int per = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
               t.setText("Battery level: " + per);




               if (per < 20) {
                   //c1.setBackgroundColor(Color.RED);
                   c1.setBackgroundColor(Color.parseColor("#FF0000"));
               } else if (per < 50) {
                   //c1.setBackgroundColor(Color.BLUE);
                   c1.setBackgroundColor(Color.parseColor("#89CFF0"));
               } else {
                   //c1.setBackgroundColor(Color.GREEN);
                   c1.setBackgroundColor(Color.parseColor("#90D26D"));
               }
               level.setProgress(per);
           }
       };
   }




   @Override
   protected void onStart() {
       super.onStart();
       registerReceiver(b, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
   }




   @Override
   protected void onStop() {
       super.onStop();
       unregisterReceiver(b);
   }
}
