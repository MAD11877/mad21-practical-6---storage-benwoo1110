package sg.edu.np.mad.madpractical;

import java.util.UUID;

import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {

    public static RealmAsyncTask delete(String id) {
        return App.realm.executeTransactionAsync(realm -> {
            User user = realm.where(User.class).equalTo("id", id).findFirst();
            if (user != null) {
                user.deleteFromRealm();
            }
        });
    }

    public static RealmResults<User> getAll() {
        return App.realm.where(User.class).findAllAsync();
    }

    @PrimaryKey public String id = UUID.randomUUID().toString();;
    @Required public String name;
    public String description;
    public boolean followed;

    public User() {
    }

    public User(String name, String description, boolean followed) {
        this.name = name;
        this.description = description;
        this.followed = followed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}
