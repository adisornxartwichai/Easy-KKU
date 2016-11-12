package kku.anaclasmos.easykku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Explicit   ต้องการประกาศตัวแปร เพื่อจะดูว่าแรมพอไหม
    private Button signInButton , signUpButton;              //ctrl + space = ใส่ type .ให้ตัวแปรโดยอัตโนมัติ   ctrl + shift + enter  = ใส่ ; ออโต้





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //Ctrl + คลิกสีฟ้า จะวาร์ปไปหน้านั้นๆ

        //Bind Widget
        signInButton = (Button) findViewById(R.id.button3);    //Alt + enter  >> cast = ใส่วงเล็บออโต้
        signUpButton = (Button) findViewById(R.id.button2);


        // Sign Up Controller
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });





    }   //Main Method
}   //Main Class

// ctrl + alt + L  == ยุบโค้ด