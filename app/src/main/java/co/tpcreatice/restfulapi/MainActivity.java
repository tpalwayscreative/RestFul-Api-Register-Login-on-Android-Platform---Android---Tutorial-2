package co.tpcreatice.restfulapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pre ;
    private SharedPreferences.Editor editor ;
    private TextView txtName ;
    private Button btnSignOut ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = (TextView) findViewById(R.id.textWelcome);
        btnSignOut = (Button) findViewById(R.id.btnSignOut);
        try {
            pre = getSharedPreferences("store",MODE_PRIVATE);
            String name =  pre.getString("name",null);
            if (name.equals(""))
            {
                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            }
            else
            {
                txtName.setText(name);
            }
        }
        catch (Exception e){

            Intent i = new Intent(getApplicationContext(),SignInActivity.class);
            startActivity(i);
        }

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor = pre.edit();
                editor.putString("name","");
                editor.commit();
                finish();

            }
        });
    }
}
