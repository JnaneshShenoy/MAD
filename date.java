public class MainActivity extends AppCompatActivity implements
AdapterView.OnItemSelectedListener {
ArrayAdapter ad;
DatePicker d;
String s2[]={"cse","ec","ise","ee","me"};
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
EdgeToEdge.enable(this);
setContentView(R.layout.activity_main);
d=findViewById(R.id.d);
Spinner s=findViewById(R.id.s);
s.setOnItemSelectedListener(this);
ad=new
ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spi
nner_dropdown_item,s2);
s.setAdapter(ad);
}
@Override
public void onItemSelected(AdapterView<?> adapterView, View
view, int i, long l) {
String s1=ad.getItem(i).toString();
String
dat=d.getDayOfMonth()+"-"+(d.getMonth()+1)+"-"+d.getYear();
String tost="Joined on:"+dat+" and selected course:"+s1;
Toast.makeText(getApplicationContext(),tost,
Toast.LENGTH_LONG).show();
}
@Override
public void onNothingSelected(AdapterView<?> adapterView) {}
}