package rzeznika.ndtClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by arzez on 22.07.2017.
 */

public class WelcomeActivity extends Activity {
    private static EditText username;
    private static String color;
    private static Spinner spinner;
    private static Button ready_btn;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);
        WelcomeButton();
        username = (EditText) findViewById(R.id.editText_username);
        spinner = (Spinner) findViewById(R.id.colorspinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.playercolors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " ist jetzt deine Farbe!", Toast.LENGTH_LONG).show();
                if (parent.getItemAtPosition(position).equals("Blau")) {
                    color = parent.getItemAtPosition(position).toString();
                } else if (parent.getItemAtPosition(position).equals("Grau")) {
                    color = parent.getItemAtPosition(position).toString();
                } else if (parent.getItemAtPosition(position).equals("Rot")) {
                    color = parent.getItemAtPosition(position).toString();
                } else if (parent.getItemAtPosition(position).equals("Orange")) {
                    color = parent.getItemAtPosition(position).toString();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void WelcomeButton() {
        username = (EditText) findViewById(R.id.editText_username);
        ready_btn = (Button) findViewById(R.id.ready_btn);

        ready_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("LOGIN", "LOGIN");
                        if (username.getText().toString().matches("")) {
                            Toast.makeText(WelcomeActivity.this, "Bitte gebe einen Username ein!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(WelcomeActivity.this, "Los geht's!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            String name = username.getText().toString();
                            intent.putExtra("STR_NAME", name);
                            intent.putExtra("STR_COLOR", color);
                            startActivity(intent);
                        }
                    }
                }
        );

    }
}
