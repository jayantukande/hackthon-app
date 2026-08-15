package com.signbridgecommunication.app.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.signbridgecommunication.app.data.dao.SignDao;
import com.signbridgecommunication.app.data.model.Category;
import com.signbridgecommunication.app.data.model.Phrase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Phrase.class}, version = 1, exportSchema = false)
public abstract class SignBridgeDatabase extends RoomDatabase {

    public abstract SignDao signDao();

    private static volatile SignBridgeDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SignBridgeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SignBridgeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SignBridgeDatabase.class, "signbridge_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                SignDao dao = INSTANCE.signDao();
                
                // Pre-populate with sample data for hackathon
                List<Category> categories = new ArrayList<>();
                categories.add(new Category("Emergency", "ic_emergency"));
                categories.add(new Category("Hospital", "ic_hospital"));
                categories.add(new Category("Daily", "ic_daily"));
                dao.insertCategories(categories);

                List<Phrase> phrases = new ArrayList<>();
                // Emergency (categoryId 1)
                phrases.add(new Phrase(1, "HELP", "मदद", "मदत", "sign_help", "Use this when you need urgent assistance."));
                phrases.add(new Phrase(1, "I NEED A DOCTOR", "मुझे डॉक्टर चाहिए", "मला डॉक्टर हवा आहे", "sign_doctor", "Use this in medical emergencies."));
                phrases.add(new Phrase(1, "CALL AN AMBULANCE", "एम्बुलेंस बुलाओ", "एम्बुलन्स बोलवा", "sign_ambulance", "Requesting emergency medical transport."));
                phrases.add(new Phrase(1, "CALL THE POLICE", "पुलिस को बुलाओ", "पोलिसांना बोलवा", "sign_police", "Requesting police assistance."));
                phrases.add(new Phrase(1, "I AM INJURED", "मैं घायल हूँ", "मी जखमी आहे", "sign_injured", "Reporting an injury."));
                phrases.add(new Phrase(1, "I NEED WATER", "मुझे पानी चाहिए", "मला पाणी हवे आहे", "sign_water", "Requesting water."));
                phrases.add(new Phrase(1, "PLEASE STAY WITH ME", "कृपया मेरे साथ रहें", "कृपया माझ्यासोबत रहा", "sign_stay", "Asking someone to stay for support."));
                
                // Hospital (categoryId 2)
                phrases.add(new Phrase(2, "I need medicine", "मुझे दवा चाहिए", "मला औषध हवे आहे", "sign_medicine", "Requesting medication."));
                phrases.add(new Phrase(2, "Where is the hospital?", "अस्पताल कहाँ है?", "रुग्णालय कोठे आहे?", "sign_hospital_loc", "Asking for directions to a hospital."));

                dao.insertPhrases(phrases);
            });
        }
    };
}