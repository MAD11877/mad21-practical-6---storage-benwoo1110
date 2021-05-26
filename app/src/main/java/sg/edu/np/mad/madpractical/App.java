package sg.edu.np.mad.madpractical;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        String realmName = "MADPractical";
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(realmName)
                .initialData(new UserSeedData())
                .build();
        realm = Realm.getInstance(config);
    }
}
