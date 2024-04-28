public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b1,b2;
    EditText e1,e2,e3;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
 
        e1=findViewById( R.id.editText1 );
        e2=findViewById( R.id.editText2 );
        e3=findViewById( R.id.editText3 );
        b1=findViewById( R.id.button1);
        b2=findViewById( R.id.button2);
 
 
        b1.setOnClickListener( this );
        b2.setOnClickListener( this );
    }
 
 
    @SuppressLint("Range")
    @Override
    public void onClick(View view) {
        String sname,sage,saddress;
        sname=e1.getText().toString();
        sage=e2.getText().toString();
        saddress=e3.getText().toString();
        try {
            db = this.openOrCreateDatabase( "stud", MODE_PRIVATE, null );
            db.execSQL( "create table if not exists test(name varchar(20),age varchar(20),address varchar(50));" );
 
 
 
 
            if (view.getId() == b1.getId()) {
                db.execSQL( "insert into test values('"+sname+"','"+sage+"','"+saddress+"');" );
                Toast.makeText( getApplicationContext(),"Row inserted successfully",Toast.LENGTH_SHORT ).show();
 
 
 
 
            }
            if(view.getId()==b2.getId())
            {
                Cursor c=db.rawQuery( "select * from test",null );
                String all="";
 
 
                if(c!=null)
                {
                    if(c.moveToFirst())
                    {
                        do{
                            sname=c.getString( c.getColumnIndex("name" ));
                            sage=c.getString(c.getColumnIndex( "age"));
                            saddress=c.getString( c.getColumnIndex("address") );
                            all=all+sname+"\t\t"+sage+"\t\t"+saddress+"\n";
 
 
                        }while(c.moveToNext());
                        Toast.makeText( this,all,Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        }
        catch (SQLiteException sq)
        {
            Toast.makeText( getApplicationContext(),"Could not create or open database",Toast.LENGTH_SHORT ).show();
        }
        finally {
            if(db!=null)
                db.close();
        }
    }
 }
 