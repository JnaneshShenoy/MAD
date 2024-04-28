public class MainActivity extends AppCompatActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_main);
   Button b = findViewById(R.id.button);
   b.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
   Intent i = new
   Intent(MediaStore.ACTION_IMAGE_CAPTURE);
   startActivityForResult(i,1);
   }
   });
   }
   @Override
   protected void onActivityResult(int requestCode, int
   resultCode, Intent data){
   if(requestCode==1 && resultCode==RESULT_OK){
   Bundle extra = data.getExtras();
   Bitmap image = (Bitmap) extra.get("data");
   BitmapDrawable drawable = new BitmapDrawable(image);
   ConstraintLayout layout = findViewById(R.id.layout);
   layout.setBackground(drawable);
   }
   super.onActivityResult(requestCode,resultCode,data);
   }
   }