package kku.anaclasmos.easykku;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit   ต้องการประกาศตัวแปร เพื่อจะดูว่าแรมพอไหม
    private Button signInButton , signUpButton;              //ctrl + space = ใส่ type .ให้ตัวแปรโดยอัตโนมัติ   ctrl + shift + enter  = ใส่ ; ออโต้
    private EditText userEditText, passwordEditText;
    private  String userString ,passwordString;
    private MyConstant myConstant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //Ctrl + คลิกสีฟ้า จะวาร์ปไปหน้านั้นๆ

        myConstant = new MyConstant();
        //Bind Widget
        signInButton = (Button) findViewById(R.id.button3);    //Alt + enter  >> cast = ใส่วงเล็บออโต้
        signUpButton = (Button) findViewById(R.id.button2);
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

        // Sign In
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Value From Edit Text
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                // Check Space
                if (userString.equals("") || passwordString.equals("")) {
                    // Have Space
                    MyAlert myAlert = new MyAlert(MainActivity.this, R.drawable.bird48,
                            getResources().getString(R.string.title_HaveSpace),
                            getResources().getString(R.string.message_HaveSpace));
                    myAlert.myDialog();
                }else {
                    // No Space
                    SynUser synUser = new SynUser(MainActivity.this);
                    synUser.execute(myConstant.getUrlGetJSON());

                }


            }  //onClick
        });


        // Sign Up Controller
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });





    }   //Main Method



        private  class  SynUser extends AsyncTask<String, Void, String>{

            //Explicit
            private Context context;
            private String[] nameStrings, phoneStrings, imageStrings;
            private String truePassword;
            private boolean aBoolean = true;


            public SynUser(Context context){
                this.context = context;
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    Request request = builder.url(params[0]).build();
                    Response response = okHttpClient.newCall(request).execute();
                    return  response.body().string();
                }catch (Exception e){
                    Log.d ("13novV1", "e doIn ==> "+ e.toString());
                    return  null;

                }


            }   //doInBack

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Log.d("13novV2", "JSON ==> " + s);

                try{
                    JSONArray jsonArray = new JSONArray(s);

                    nameStrings = new String[jsonArray.length()];
                    phoneStrings = new String[jsonArray.length()];
                    imageStrings = new String[jsonArray.length()];

                    for (int i=0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        nameStrings[i] = jsonObject.getString("Name");
                        phoneStrings[i] = jsonObject.getString("Phone");
                        imageStrings[i] = jsonObject.getString("Image");

                        Log.d("13novV3", "Name(" + i + ") ==> " + nameStrings[i]);


                        //Check User
                        if (userString.equals(jsonObject.getString("User"))) {
                            aBoolean = false;
                            truePassword = jsonObject.getString("Password");
                        }

                    }   // for วนลูปตามจำนวน record ที่มีอยุ่

                    if (aBoolean) {
                        // User false
                        MyAlert myAlert = new MyAlert(context, R.drawable.kon48,
                                getResources().getString(R.string.title_UserFalse),
                                getResources().getString(R.string.message_UserFalse));
                        myAlert.myDialog();

                    } else if (passwordString.equals(truePassword)) {
                        //Password true
                        Toast.makeText(context,"Welcome", Toast.LENGTH_SHORT).show();

                        //Intent To Service
                        Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                        intent.putExtra("Name", nameStrings);
                        intent.putExtra("Phone", phoneStrings);
                        intent.putExtra("Image", imageStrings);
                        startActivity(intent);
                        finish();


                    } else {
                        //Password false
                        MyAlert myAlert = new MyAlert(context, R.drawable.rat48,
                                getResources().getString(R.string.title_PasswordFalse),
                                getResources().getString(R.string.message_PasswordFalse));
                        myAlert.myDialog();

                    }

                }catch(Exception e){
                        e.printStackTrace();
                }

            }   //onPost
        }  //SynUser
}   //Main Class

// ctrl + alt + L  == ยุบโค้ด