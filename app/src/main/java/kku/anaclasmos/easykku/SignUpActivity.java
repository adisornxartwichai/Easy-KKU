package kku.anaclasmos.easykku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    //Explicit

    private EditText nameEditText, phoneEditText, userEditText, passwordEditText;

    private ImageView imageView;
    private Button button;
    private String nameString, phoneString, userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText4);
        phoneEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText);

        imageView = (ImageView) findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.button);


        //sign up controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get value From Edit Text
                nameString = nameEditText.getText().toString().trim();
                phoneString = phoneEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                // check space
                if (nameString.equals("") || phoneString.equals("") || userString.equals("") || passwordString.equals("")) {
                    // Have Space
                    Log.d("12novV1", "Have Space");
                    MyAlert myAlert = new MyAlert(SignUpActivity.this, R.drawable.doremon48, "มีช่องว่าง", "กรุณากรอกให้ครบทุกช่องครับ");
                    myAlert.myDialog();

                }
            }   //OnClick
        });

    } //main method


} //main class
