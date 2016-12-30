package majorproject.kone.in.collegebudy.Utility;

import android.app.Application;

/**
 * Created by kartikey on 30/12/16.
 */

public class CollegeBuddyStudentApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initialiseSingletons();
    }

    private void initialiseSingletons() {
        SharedPreferencesSingleton.initSharedPreference(this);
    }
}
