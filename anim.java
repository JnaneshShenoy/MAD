public class MainActivity extends AppCompatActivity {
   private ImageView img;
   private Animation blink, move;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       img = findViewById(R.id.img);


       blink = AnimationUtils.loadAnimation(this,
               R.anim.blink);
       move = AnimationUtils.loadAnimation(this,
               R.anim.move);
       Button blinkB = findViewById(R.id.blinkB);
       Button moveB = findViewById(R.id.moveB);
       blinkB.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v) {
               starImage.startAnimation(blink);
           }
       });
       moveB.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v) {
               starImage.startAnimation(move);
           }
       });
   }
}

//Blink
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
   <alpha
       android:fromAlpha="1.0"
       android:toAlpha="0.0"
       android:duration="500"
       android:repeatMode="reverse"
       android:repeatCount="infinite" />
</set>

//Move
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
   <translate
       android:fromXDelta="0%"
       android:toXDelta="50%"
       android:fromYDelta="0%"
       android:toYDelta="50%"
       android:duration="1000"
       android:repeatMode="reverse"
       android:repeatCount="infinite" />
</set>

//Rotate:
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
   android:fromDegrees="0"
   android:pivotX="50%"
   android:pivotY="50%"
   android:toDegrees="360"
   android:duration="1000"
   android:repeatCount="infinite"/>

//Zoom in-out:
<set xmlns:android="http://schemas.android.com/apk/res/android"
   android:fillAfter="true" >

   <scale
       android:duration="500"
       android:fromXScale="1.0"
       android:fromYScale="1.0"
       android:pivotX="50%"
       android:pivotY="50%"
       android:toXScale="2.0"
       android:toYScale="2.0" >
   </scale>
   <scale
       android:duration="500"
       android:fromXScale="2.0"
       android:fromYScale="2.0"
       android:pivotX="50%"
       android:pivotY="50%"
       android:startOffset="500"
       android:toXScale="1.0"
       android:toYScale="1.0" >
   </scale>
</set>
