package com.williamhenry.insantani;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by agungwy on 10/29/2015.
 */
public class Registration extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        Bundle extras = getIntent().getExtras();
        Bundle item = (Bundle) extras.get("User");
        TextView email = (TextView) findViewById(R.id.register_email);
        TextView password = (TextView) findViewById(R.id.register_password);
        email.setText(item.getString("Email"));
        password.setText(item.getString("Password"));




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
