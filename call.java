<activity android:name="PreviewActivity"/>

public class MainActivity extends AppCompatActivity {
   Button preview, selectImage;
   EditText name, email, phone, quali;
   RadioButton male, female;
   ImageView profileImage;
   private static final int PICK_IMAGE = 1;
   Uri imageUri;




   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.input);




       preview = findViewById(R.id.button);
       selectImage = findViewById(R.id.select_image_button);
       profileImage = findViewById(R.id.profile_image);




       name = findViewById(R.id.e1);
       email = findViewById(R.id.e2);
       phone = findViewById(R.id.e3);
       quali = findViewById(R.id.e4);




       male = findViewById(R.id.male);
       female = findViewById(R.id.female);




       selectImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openGallery();
           }
       });




       preview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(), Output.class);
               i.putExtra("name", name.getText().toString());
               i.putExtra("email", email.getText().toString());
               i.putExtra("phone", phone.getText().toString());
               i.putExtra("quali", quali.getText().toString());
               i.putExtra("gender", (male.isChecked() ? "Male" : "Female"));
               if (imageUri != null) {
                   i.putExtra("imageUri", imageUri.toString());
               }
               startActivity(i);
           }
       });
   }




   private void openGallery() {
       Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       startActivityForResult(gallery, PICK_IMAGE);
   }




   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);




       if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
           imageUri = data.getData();
           try {
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
               profileImage.setImageBitmap(bitmap);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
}

public class Output extends AppCompatActivity {
   TextView name, email, phone, quali, gender;
   ImageView profileImage;
   private static final int PICK_IMAGE = 1;


   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.output);


       name = findViewById(R.id.t1);
       email = findViewById(R.id.t2);
       phone = findViewById(R.id.t3);
       quali = findViewById(R.id.t4);
       gender = findViewById(R.id.t5);
       profileImage = findViewById(R.id.profile_image); // Add ImageView


       Intent i = getIntent();
       name.append("\t" + i.getStringExtra("name"));
       email.append("\t" + i.getStringExtra("email"));
       phone.append("\t" + i.getStringExtra("phone"));
       quali.append("\t" + i.getStringExtra("quali"));
       gender.append("\t" + i.getStringExtra("gender"));


       // Load and display the profile image if available
       String imageUriString = i.getStringExtra("imageUri");
       if (imageUriString != null) {
           Uri imageUri = Uri.parse(imageUriString);
           try {
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
               profileImage.setImageBitmap(bitmap);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
}
