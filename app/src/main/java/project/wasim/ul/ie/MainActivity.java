/*
name: Wasim Ghazal Aswad
ID: 17193559
Last update: 13/05/2020
 */

package project.wasim.ul.ie;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;


public class MainActivity extends Activity {


    Button MailButton;
    Button NextButton;
    ImageView logo;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.AppImage);
        logo.setImageDrawable(getDrawable(R.mipmap.ic_launcher_round));


        MailButton = (Button) findViewById(R.id.email);
        MailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.Email)));
                try {
                    MainActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });


        NextButton = (Button)findViewById(R.id.Enter);

        // Add an action to the button
        NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    goToMovieActivity();

            }
        });
        NextActivity();

    }
// I get the code from here https://stackoverflow.com/questions/37485083/how-to-move-from-one-activity-to-another-after-certain-time-automatically-in-c-s
    public void NextActivity()
    {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this,MoviesActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 60000); // move to the MoviesActivity after one min

    }


    public void goToMovieActivity() {

        // called this mathed to go to the NextActivity by calling the class.
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);

    }




}