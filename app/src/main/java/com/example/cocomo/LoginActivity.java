package com.example.cocomo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocomo.Const.ReferenceManager;
import com.example.cocomo.Const.Untils;
import com.example.cocomo.model.User;
import com.example.cocomo.retrofit.ApiCocomo;
import com.example.cocomo.retrofit.RetrofitClient;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {
    TextView register, regoogle;
    EditText emailedt, passwordedt;
    Button loginbtn;
    CheckBox show;
    ImageView eyeimg;
    CompositeDisposable disposable = new CompositeDisposable();
    ApiCocomo apiCocomo;
    ReferenceManager manager;
    LoginButton loginButton;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.cocomo",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {
        }
        catch (NoSuchAlgorithmException e) {
        }
        process();
        google();
    }
    private void process() {
        if(manager.getString("username")!=null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        eyeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show.isChecked()) {
                    show.setChecked(false);
                    eyeimg.setImageResource(R.drawable.eye);
                    passwordedt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    show.setChecked(true);
                    eyeimg.setImageResource(R.drawable.hidden);
                    passwordedt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount();
            }
        });
    }

    private void google() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        regoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signIntent = gsc.getSignInIntent();
        startActivityForResult(signIntent, Untils.CODE_GOOGLE);
    }
    private void facebook() {
        loginButton = findViewById(R.id.login_button);
        loginButton.setLoginText("Continue with Facebook");
        loginButton.setLogoutText("Continue with Facebook");
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, this);
    }
    private void loginAccount() {
        String email1 = emailedt.getText().toString().trim();
        String password1 = passwordedt.getText().toString().trim();
        if(checkInput()){
            checkAccount(email1, password1);
        }
    }


    private void checkAccount(String email1, String password1) {
        disposable.add(apiCocomo.loginUser(email1, password1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if(userModel.isSuccess()){
                                User user = (User) userModel.getData();
                                manager.putString("_id", user.get_id());
                                manager.putString("username", user.getUsername());
                                manager.putString("email", user.getEmail());
                                manager.putString("avatar", user.getAvatar());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else{
                                text("Email or Password is wrong!");
                            }
                        },
                        throwable -> {
                            text(throwable.getMessage());
                            Log.e("er",throwable.getMessage());
                        }
                ));
    }

    private void anhXa() {
        register = findViewById(R.id.registertext);
        emailedt = findViewById(R.id.email);
        passwordedt = findViewById(R.id.password);
        eyeimg = findViewById(R.id.eyeimg);
        show = findViewById(R.id.showPassword);
        loginbtn = findViewById(R.id.loginaccount);
        manager = new ReferenceManager(LoginActivity.this);
        regoogle = findViewById(R.id.login_google);
        apiCocomo = RetrofitClient.getRetrofit(Untils.BASE_URL).create(ApiCocomo.class);
    }

    boolean checkInput(){
        if(emailedt.getText().toString().trim().isEmpty()){
            text("Please input Email!");
            emailedt.requestFocus();
            return false;
        }else if(passwordedt.getText().toString().trim().isEmpty()){
            text("Please input Password!");
            passwordedt.requestFocus();
            return false;
        }else return true;
    }
    void text(String v){
        Toast.makeText(this, v+"", Toast.LENGTH_SHORT).show();
    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("kq", response.getJSONObject().toString());
                        try {
                            String name1 = object.getString("name");
                            String email1 = object.getString("email");
                            String password1 = object.getString("id");
                            //truyen data vao
                            register(name1, email1, password1);
                            checkAccount(email1, password1);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        Bundle parame = new Bundle();
        parame.putString("fields", "id,name,first_name,email");
        graphRequest.setParameters(parame);
        graphRequest.executeAsync();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        result();
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Untils.CODE_GOOGLE){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
                if(acct!=null){
                    String person = acct.getDisplayName();
                    String email = acct.getEmail();
                    String password = acct.getId();
                    register(person, email, password);
                    checkAccount(email, password);
                }
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void register(String name1, String email1, String password1) {
        disposable.add(apiCocomo.registerUser(name1, email1, password1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resultModel -> {
                            if(resultModel.isSuccess()){
                                text(resultModel.getMessage());
                            }else{
                                LoginManager.getInstance().logOut();
                                text("Tài khoản đã tồn tại!");
                            }
                        },
                        throwable -> {
                            text("Error!");
                        }
                ));
    }

    protected void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}