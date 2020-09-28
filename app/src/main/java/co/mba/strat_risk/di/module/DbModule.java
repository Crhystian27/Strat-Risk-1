package co.mba.strat_risk.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import javax.inject.Singleton;

import co.mba.strat_risk.data.SRDataBase;
import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class DbModule {
    private static final String DATA_BASE_NAME = "SRV01.db";


    @Provides
    @Singleton
    SRDataBase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                SRDataBase.class, DATA_BASE_NAME)
                .allowMainThreadQueries().addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                }).build();
    }

}
