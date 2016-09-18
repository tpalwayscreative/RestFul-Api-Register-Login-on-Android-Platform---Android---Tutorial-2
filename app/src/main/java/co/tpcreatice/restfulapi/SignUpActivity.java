package co.tpcreatice.restfulapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


    private Button btnSignIn,btnSignUp ;
    private EditText edtEmail,edtPassword,edtName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassWord);
        edtName = (EditText) findViewById(R.id.edtName);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if(email.equals("") || password.equals("") || name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter full field",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassWord(password);
                    new AsyncTaskUser(SignUpActivity.this,user,false).execute("http://tpalwayscreative.esy.es/task_manager/v1/register");
                }

            }
        });
    }
}
