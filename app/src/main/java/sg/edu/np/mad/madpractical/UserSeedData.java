package sg.edu.np.mad.madpractical;

import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;

import io.realm.Realm;

@ParametersAreNonnullByDefault
public class UserSeedData implements Realm.Transaction {

    @Override
    public void execute(Realm realm) {
        for (int i = 0; i < 20; i++) {
            realm.insertOrUpdate(new User(
                    "Name" + randomInt(),
                    "Description " + randomInt(),
                    randomInt() % 2 == 1
            ));
        }
    }

    private static int randomInt() {
        return new Random().nextInt();
    }
}
