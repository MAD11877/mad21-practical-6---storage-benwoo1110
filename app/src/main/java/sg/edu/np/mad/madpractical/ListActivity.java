package sg.edu.np.mad.madpractical;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    static List<User> usersList = new ArrayList<>();

    static {
        for (int i = 0; i < 20; i++) {
            usersList.add(new User(
                    "Name" + randomInt(),
                    "Description " + randomInt(),
                    i,
                    randomInt() % 2 == 1
            ));
        }
    }

    private static int randomInt() {
        return new Random().nextInt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.rv);

        UsersAdapter adapter = new UsersAdapter(usersList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.addItemDecoration(divider);

        new ItemTouchHelper(new SwipeToDeleteCallback() {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int targetPos = viewHolder.getAdapterPosition();
                usersList.remove(targetPos);
                adapter.notifyItemRemoved(targetPos);
            }
        }).attachToRecyclerView(recyclerView);
    }
}