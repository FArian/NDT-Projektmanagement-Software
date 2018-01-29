package com.androidjson.firebasegooglelogin_androidjsoncom;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.StartActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.connection.Client;
import com.androidjson.firebasegooglelogin_androidjsoncom.hps.activitys.StarterActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.json.ToJson;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.DatePickerFragment;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

// Importing Google GMS Auth API Libraries.
//Import other Classes

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    // TAG is for show some tag logs in LOG screen.
    public static final String TAG = "MainActivity";

    // Request sing in code. Could be anything as you required.
    public static final int RequestSignInCode = 7;

    // Firebase Auth Object.
    public FirebaseAuth firebaseAuth;

    // Google API Client object.
    public GoogleApiClient googleApiClient;

    // Sing out and next buttons
    private Button signOut_btn, birthday_btn;

    // Google Sign In button .
    private com.google.android.gms.common.SignInButton signInButton;

    // TextView to Show Login User Email and Name.
    private TextView LoginUserName, LoginUserEmail, LoginUserBirthday;

    private WebSocket webSocket;
    private OkHttpClient okHttpClient;
    private Client client;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private ImageView user_pic, radiographer_pic, health_physic_pic;
    private Personal personal;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpClient = new OkHttpClient();
        client = new Client();
        gson = new Gson();
        //create connection with Websocket
        webSocket = client.getWebSocket();
        client.getClient();
        bindViews();
        personal = new Personal();
        // Getting Firebase Auth Instance into firebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();
        // Hiding the TextView on activity start up time.
        LoginUserEmail.setVisibility(View.GONE);
        LoginUserName.setVisibility(View.GONE);
        LoginUserBirthday.setVisibility(View.GONE);

        signInButton.setOnClickListener(this);
        health_physic_pic.setOnClickListener(this);
        radiographer_pic.setOnClickListener(this);
        signOut_btn.setOnClickListener(this);
        birthday_btn.setOnClickListener(this);
        LoginUserBirthday.setOnClickListener(this);
        //TODO the radiographer_pic Button should be gone , now just for test is visible
        radiographer_pic.setVisibility(View.VISIBLE);


        // Creating and Configuring Google Sign In object.
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestScopes(new Scope("profile"))
                .build();


        // Creating and Configuring Google Api Client.
        googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(MainActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @SuppressLint("ResourceAsColor")
    private void bindViews() {

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        user_pic = (ImageView) findViewById(R.id.user_pic);
        signOut_btn = (Button) findViewById(R.id.sign_out);
        radiographer_pic = (ImageView) findViewById(R.id.radiographer_pic);
        health_physic_pic = (ImageView) findViewById(R.id.health_physic_pic);
        birthday_btn = (Button) findViewById(R.id.birthday_btn);
        LoginUserName = (TextView) findViewById(R.id.textViewName);
        LoginUserEmail = (TextView) findViewById(R.id.textViewEmail);
        LoginUserBirthday = (TextView) findViewById(R.id.birthday_txt);
        signInButton = (com.google.android.gms.common.SignInButton) findViewById(R.id.sign_in_button);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        LoginUserBirthday.setText(" Birthday : " + currentDateString);
        this.birthday = currentDateString;

    }


    private void goToNextPage() {
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        personal = new Personal(getFirstName(), getLastName());
        personal.setEmail(getEmail());

        String myJson = gson.toJson(personal);
        intent.putExtra("Personal", myJson);
        startActivity(intent);
    }

    private void goToNextPageHPS() {
        Intent intent = new Intent(getApplicationContext(), StarterActivity.class);
        startActivity(intent);
    }


    // Sign In function Starts From Here.
    public void UserSignInMethod() {
        // Passing Google Api Client into Intent.
        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(AuthIntent, RequestSignInCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestSignInCode) {

            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (googleSignInResult.isSuccess()) {

                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();

                FirebaseUserAuth(googleSignInAccount);


            }

        }
    }

    public void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {

        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

        Toast.makeText(MainActivity.this, "" + authCredential.getProvider(), Toast.LENGTH_LONG).show();

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> AuthResultTask) {

                        if (AuthResultTask.isSuccessful()) {

                            // Getting Current Login user details.
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String name = firebaseUser.getDisplayName();
                            setFirstName(parsingName(name)[0].toString());
                            setLastName(parsingName(name)[1].toUpperCase());
                            String email = firebaseUser.getEmail().toString();
                            setEmail(email);
                            personal.setFirstName(getFirstName());
                            personal.setLastName(getLastName());
                            personal.setEmail(email);
                            client.getWebSocket().send(ToJson.message("NEW_USER_ASK", email, getEmail()).toString());
                            if (!client.isNewUser()) {
                                birthday_btn.setVisibility(View.VISIBLE);
                            }
                            // Showing Log out button.
                            signOut_btn.setVisibility(View.VISIBLE);
                            radiographer_pic.setVisibility(View.VISIBLE);
                            health_physic_pic.setVisibility(View.VISIBLE);
                            // Hiding Login in button.
                            signInButton.setVisibility(View.GONE);
                            // Showing the TextView.
                            LoginUserEmail.setVisibility(View.VISIBLE);
                            LoginUserName.setVisibility(View.VISIBLE);
                            LoginUserBirthday.setVisibility(View.VISIBLE);
                            // Setting up name into TextView.
                            LoginUserName.setText(" Name : " + firebaseUser.getDisplayName().toString());
                            // Setting up Email into TextView.
                            LoginUserEmail.setText("E-Mail:" + firebaseUser.getEmail().toString());
                            String str = firebaseUser.getPhotoUrl().toString();
                            Picasso.with(getApplicationContext()).load(str).into(user_pic);
                            //Glide.with(getApplicationContext()).load(str).into(user_pic);

                            Toast.makeText(MainActivity.this, " Welcome " + getFirstName() + " " + getLastName(),
                                    Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void UserSignOutFunction() {

        // Sing Out the User.
        firebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                // Write down your any code here which you want to execute After Sign Out.

                // Printing Logout toast message on screen.
                Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_LONG).show();

            }
        });

        // After logout Hiding sign out button.
        signOut_btn.setVisibility(View.GONE);
        // After logout Hiding next button.
        radiographer_pic.setVisibility(View.GONE);
        health_physic_pic.setVisibility(View.GONE);
        birthday_btn.setVisibility(View.GONE);

        // After logout setting up email and name to null.
        LoginUserName.setText(null);
        LoginUserEmail.setText(null);
        LoginUserBirthday.setText(null);

        // After logout setting up login button visibility to visible.
        signInButton.setVisibility(View.VISIBLE);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    /**
     * @param displayName
     * @return first name in position [0] and last name in position [1]
     */
    public String[] parsingName(String displayName) {
        String[] result = new String[2];
        int start = displayName.indexOf(' ');
        if (start >= 0) {
            result[0] = displayName.substring(0, start);
            result[1] = displayName.substring(start + 1, displayName.length());
        }
        return result;

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.health_physic_pic:
                Toast.makeText(MainActivity.this, "Health Physics User", Toast.LENGTH_LONG).show();
                goToNextPageHPS();
                break;
            case R.id.radiographer_pic:
                // with radiographer button after login go to next page
                birthday_btn.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Radiographer User", Toast.LENGTH_LONG).show();
                goToNextPage();
                break;
            case R.id.sign_out:
                // Adding Click Listener to User Sign Out button.
                UserSignOutFunction();
                break;
            case R.id.sign_in_button:
                // Adding Click listener to User Sign in Google button.
                UserSignInMethod();
                break;
            case R.id.birthday_btn:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), TAG);
                Toast.makeText(MainActivity.this, "Birthday", Toast.LENGTH_LONG).show();
                break;
            case R.id.birthday_txt:
                birthday_btn.setVisibility(View.VISIBLE);
                break;
        }

    }
}