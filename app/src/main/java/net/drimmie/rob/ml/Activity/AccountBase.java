package net.drimmie.rob.ml.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import net.drimmie.rob.ml.R;

/**
 * Created by robdrimmie on 2018-02-11.
 */

public class AccountBase extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.quit, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quit:
                quitToMain();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void quitToMain() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );

        // TODO: Move preference management into single place
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(
                getString(R.string.preference_opened),
                getResources().getInteger(R.integer.preference_opened_false)
        );
        editor.apply();

        finish();
    }
}
