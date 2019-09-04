package app.vala;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {

    // Click this button to let user select mp3 file.
    private Button browseAudioFileButton;

    // Start play audio button.
    private Button startButton;

    // Pause playing audio button.
    private Button pauseButton;

    // Stop playing audio button.
    private Button stopButton;
    private Button playButton;

    // Used when user select audio file.
    private static final int REQUEST_CODE_SELECT_AUDIO_FILE = 1;

    // Used when user require android READ_EXTERNAL_PERMISSION.
    private static final int REQUEST_CODE_READ_EXTERNAL_PERMISSION = 2;

    // Used when update audio progress thread send message to progress bar handler.
    private static final int UPDATE_AUDIO_PROGRESS_BAR = 3;

    // Used to control audio (start, pause , stop etc).
    private MediaPlayer audioPlayer = null;

    // Save user selected or inputted audio file unique resource identifier.
    private Uri audioFileUri = null;

    // Used to distinguish log data.
    public static final String TAG_PLAY_AUDIO = "PLAY_AUDIO";

    // The thread that send message to audio progress handler to update progress every one second.
    private Thread updateAudioPalyerProgressThread = null;

    // Record whether audio is playing or not.
    private boolean audioIsPlaying = false;



    private MediaPlayer mediaPlayer = null;
    private static boolean toogleEnterOnOff = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void allButtonClickHandler(View view) {
        switch (view.getId()){
            case R.id.playtone: break;
            case R.id.recordtone:
                navigateToRecord();
                break;
            case R.id.playsong:
                PlaySong();
                break;
            case R.id.backButton:
                backToHome();
                break;
            case R.id.pausesong:
                pauseSong();
                break;
            case R.id.stopsong:
                stopSong();
                break;
        }
    }

    private void stopSong() {
        toogleEnterOnOff = false;
        startButton = findViewById(R.id.playsong);
        pauseButton = findViewById(R.id.pausesong);
        stopButton = findViewById(R.id.stopsong);
        if(audioIsPlaying)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            startButton.setEnabled(true);
            pauseButton.setEnabled(false);
            stopButton.setEnabled(false);

            updateAudioPalyerProgressThread = null;
            audioIsPlaying = false;
        }
    }

    private void pauseSong() {
        startButton = findViewById(R.id.playsong);
        pauseButton = findViewById(R.id.pausesong);
        stopButton = findViewById(R.id.stopsong);
        if(audioIsPlaying)
        {
            mediaPlayer.pause();
            startButton.setEnabled(true);
            pauseButton.setEnabled(false);
            stopButton.setEnabled(true);
            audioIsPlaying = false;

        }
    }

    void PlaySong() {
        startButton = findViewById(R.id.playsong);
        pauseButton = findViewById(R.id.pausesong);
        stopButton = findViewById(R.id.stopsong);
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        mediaPlayer.start();
        audioIsPlaying = true;
        processShowRahButtonClick();
    }
    private void processShowRahButtonClick() {
         pauseButton = findViewById(R.id.pausesong);
         stopButton = findViewById(R.id.stopsong);

        if (toogleEnterOnOff) {
            stopButton.setVisibility(View.INVISIBLE);
            pauseButton.setVisibility(View.INVISIBLE);
            toogleEnterOnOff = false;
        } else {
            stopButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            toogleEnterOnOff = true;
        }
    }
    private void backToHome(){
        Intent homeIntent = new Intent(Settings.this, MainActivity.class);
        stopSong();
        startActivity(homeIntent);
    }

    private void navigateToRecord(){

    }
}
