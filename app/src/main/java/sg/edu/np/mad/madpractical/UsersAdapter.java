package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private final List<User> users;

    public UsersAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutType = viewType == 1 ? R.layout.rv_item : R.layout.rv_item_2;
        View item = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(item);

        Context context = item.getContext();
        viewHolder.image.setOnClickListener(v -> new AlertDialog.Builder(context)
                .setTitle("Profile")
                .setMessage(viewHolder.name.getText())
                .setPositiveButton("View", (dialog, which) -> {
                    if (viewHolder.index == -1) {
                        Toast.makeText(context, "Error! Invalid id.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent viewProfile = new Intent(context, MainActivity.class);
                    viewProfile.putExtra("id", viewHolder.index);
                    context.startActivity(viewProfile);
                })
                .setNegativeButton("Close", null)
                .show());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User targetUser = this.users.get(position);
        holder.index = position;
        holder.name.setText(targetUser.name);
        holder.description.setText(targetUser.description);
    }

    @Override
    public int getItemViewType(int position) {
        return this.users.get(position).name.endsWith("7") ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
