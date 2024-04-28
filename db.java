public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private EditText usnEditText, nameEditText, phoneEditText, searchUsnEditText;
   private Button insertButton, searchButton;
   private SQLiteDatabase database;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);


       usnEditText = findViewById(R.id.usn_edit_text);
       nameEditText = findViewById(R.id.name_edit_text);
       phoneEditText = findViewById(R.id.phone_edit_text);
       searchUsnEditText = findViewById(R.id.search_usn_edit_text);
       insertButton = findViewById(R.id.insert_button);
       searchButton = findViewById(R.id.search_button);


       insertButton.setOnClickListener(this);
       searchButton.setOnClickListener(this);
   }


   @SuppressLint("Range")
   @Override
   public void onClick(View view) {
       String usn, name, phone;


       usn = usnEditText.getText().toString();
       name = nameEditText.getText().toString();
       phone = phoneEditText.getText().toString();


       try {
           database = this.openOrCreateDatabase("stud01", MODE_PRIVATE, null);
           database.execSQL("CREATE TABLE IF NOT EXISTS test(usn VARCHAR(20), name VARCHAR(20), phone VARCHAR(20));");


           if (view.getId() == R.id.insert_button) {
               database.execSQL("INSERT INTO test VALUES('" + usn + "','" + name + "','" + phone + "');");
               Toast.makeText(getApplicationContext(), "Row inserted successfully", Toast.LENGTH_SHORT).show();
           } else if (view.getId() == R.id.search_button) {
               Cursor cursor = database.rawQuery("SELECT * FROM test WHERE usn='" + searchUsnEditText.getText().toString() + "';", null);
               if (cursor.getCount() != 1) {
                   Toast.makeText(getApplicationContext(), "Invalid USN", Toast.LENGTH_SHORT).show();
               } else {
                   cursor.moveToNext();
                   Intent intent = new Intent(Intent.ACTION_DIAL);
                   intent.setData(Uri.parse("tel:" + cursor.getString(cursor.getColumnIndex("phone"))));
                   startActivity(intent);
               }
           }
       } catch (SQLiteException e) {
           Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
       } finally {
           if (database != null) {
               database.close();
           }
       }
   }
}
