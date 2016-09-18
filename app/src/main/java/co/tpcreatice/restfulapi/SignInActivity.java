package co.tpcreatice.restfulapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private Button btnSignIn,btnSignUp ;
    private EditText edtEmail,edtPassword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassWord);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if(email.equals("") || password.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter full field",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassWord(password);
                    new AsyncTaskUser(SignInActivity.this,user,true).execute("http://tpalwayscreative.esy.es/task_manager/v1/login");
                }

            }
        });


    }
}
