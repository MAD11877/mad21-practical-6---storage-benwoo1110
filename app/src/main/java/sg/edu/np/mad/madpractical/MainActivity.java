package sg.edu.np.mad.madpractical;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private User user;

    private TextView textViewName;
    private TextView textViewDescription;
    private Button buttonFollow;
    private Button buttonMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.textViewName = findViewById(R.id.textViewName);
        this.textViewDescription = findViewById(R.id.textViewDescription);
        this.buttonFollow = findViewById(R.id.btnFollow);
        this.buttonMessage = findViewById(R.id.buttonMessage);

        int id = getIntent().getIntExtra("id", 0);
        User targetUser = ListActivity.usersList.get(id);

        this.setUser(targetUser);
        this.buttonFollow.setOnClickListener(v -> {
            this.user.setFollowed(!this.user.isFollowed());
            this.updateFollowState();
            this.toastUserFollowState();
        });
    }

    private void setUser(User user) {
        this.user = user;
        this.updateUserInfo();
        this.updateFollowState();
    }

    private void updateUserInfo() {
        this.textViewName.setText(user.getName());
        this.textViewDescription.setText(user.getDescription());
    }

    private void updateFollowState() {
        if (this.user.isFollowed()) {
            this.buttonFollow.setText(R.string.unfollow);
        } else {
            this.buttonFollow.setText(R.string.follow);
        }
    }

    private void toastUserFollowState() {
        if (this.user.isFollowed()) {
            Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
        }
    }
}