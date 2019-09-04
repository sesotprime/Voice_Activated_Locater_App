package app.vala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Settings  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void navigateTosettingActivityUI() {
        Intent settingIntent = new Intent(MainActivity.this, Settings.class);
        startActivity(settingIntent);
    }

    public void allButtonClickHandler(View view) {
        if(view.getId()==R.id.settings){
            navigateTosettingActivityUI();
        }
        else if(view.getId()==R.id.logo)
        {
            PlaySong();
        }
    }
}
