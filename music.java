<application/>


<service
   android:name=".MusicPlayerService"
   android:foregroundServiceType="mediaPlayback"
   android:exported="false" />


<activity/>

MusicPlayerService.java
public class MusicPlayerService extends Service {
   private MediaPlayer player;


   @Override
   public IBinder onBind(Intent intent) {
       return null;
   }
   @Override
   public void onCreate() {
       super.onCreate();
       player = MediaPlayer.create(this, R.raw.music); // replace with your music file
       player.setLooping(true);
   }
   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
       player.start();
       return START_STICKY;
   }
   @Override
   public void onDestroy() {
       super.onDestroy();
       player.stop();
   }
}

MainActivity.java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


   private Button startButton;
   private Button stopButton;
   private Button changeColor;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);


       startButton = findViewById(R.id.start_button);
       stopButton = findViewById(R.id.stop_button);
       changeColor = findViewById(R.id.changeColor);


       startButton.setOnClickListener(this);
       stopButton.setOnClickListener(this);


       changeColor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ConstraintLayout c = findViewById(R.id.c1);
               Random gen = new Random();
               c.setBackgroundColor(Color.rgb(gen.nextInt(), gen.nextInt(), gen.nextInt()));
           }
       });
   }
   @Override
   public void onClick(View view) {
       if (view == startButton) {
           startService(new Intent(this, MusicPlayerService.class));
       } else if (view == stopButton) {
           stopService(new Intent(this, MusicPlayerService.class));
       }
   }
   @Override
   public void onPointerCaptureChanged(boolean hasCapture) {
       super.onPointerCaptureChanged(hasCapture);
   }
}
