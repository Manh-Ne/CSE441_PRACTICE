import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.ex09_mp3.R;

public class Myservice extends Service {
    MediaPlayer mymedia;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the MediaPlayer in onCreate
        mymedia = MediaPlayer.create(Myservice.this, R.raw.mymedia);
        if (mymedia != null) {
            mymedia.setLooping(true);  // Set looping
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Check if the media player is ready
        if (mymedia != null) {
            if (mymedia.isPlaying()) {
                mymedia.pause();  // Pause if playing
            } else {
                mymedia.start();  // Start if not playing
            }
        } else {
            // If mymedia is null, recreate it
            mymedia = MediaPlayer.create(Myservice.this, R.raw.mymedia);
            if (mymedia != null) {
                mymedia.setLooping(true);
                mymedia.start();  // Start the media after creating
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mymedia != null) {
            mymedia.stop();  // Stop playback when service is destroyed
            mymedia.release();  // Release resources
            mymedia = null;
        }
    }
}