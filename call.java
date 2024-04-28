public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2;
    EditText e1,e2,e3,e4;
    SQLiteDatabase db=null;
 
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        e1=findViewById( R.id.editText1 );
        e2=findViewById( R.id.editText2 );
        e3=findViewById( R.id.editText3 );
 
 
        e4=findViewById( R.id.editText4 );
 
 
        b1=findViewById( R.id.button1 );
        b2=findViewById( R.id.button2 );
        //ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.CALL_PHONE},1 );
        b1.setOnClickListener( this );
        b2.setOnClickListener( this );
    }
 
 
    @SuppressLint("Range")
    @Override
    public void onClick(View view) {
        String name, usn,ph;
        usn = e1.getText().toString();
        name = e2.getText().toString();
        ph = e3.getText().toString();
        try {
            db = this.openOrCreateDatabase( "stud01", MODE_PRIVATE, null );
            db.execSQL("create table if not exists test(usn varchar(20),name varchar(20),phone varchar(20));");
            if(view.getId()==b1.getId())
            {
                db.execSQL( "insert into test values('"+usn+"','"+name+"','"+ph+"');" );
                Toast.makeText(getApplicationContext(),"Row inserted succesfully",Toast.LENGTH_SHORT ).show();
            }
            if(view.getId()==b2.getId())
            {
                Cursor c=db.rawQuery( "select * from test where usn='"+e4.getText().toString()+"';",null );
                if(c.getCount()!=1)
                {
                    Toast.makeText( getApplicationContext(),"invalid",Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    c.moveToNext();
                    ph=c.getString( c.getColumnIndex( "phone") ) ;
                    Intent i=new Intent( Intent.ACTION_DIAL );
                    i.setData( Uri.parse("tel:"+ph) );
                    startActivity( i );
                }
            }
        }
        catch (SQLiteException e)
        {
            Toast.makeText( getApplicationContext(),"Error",Toast.LENGTH_SHORT ).show();
 
 
        }
        finally {
            if(db!=null)
                db.close();
        }
    }
 }
 