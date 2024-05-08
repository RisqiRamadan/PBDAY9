package com.example.pbday9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.pbday9.data.sessionManager;

public class MainActivity extends AppCompatActivity {

    sessionManager sessionmanager ;
    TextView tvname, tvusername;
    String username,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvusername = findViewById(R.id.tvusername);
        tvname = findViewById(R.id.tvnama);

        sessionmanager = new sessionManager(this);
        if (!sessionmanager.isLogIng()){
            goLogin();
        }

        username = sessionmanager.getUser().get(sessionManager.USERNAME);
        name = sessionmanager.getUser().get(sessionManager.NAME);

        tvusername.setText(username);
        tvname.setText(name);
    }

    private void goLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout){
            sessionmanager.logout();
            goLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}