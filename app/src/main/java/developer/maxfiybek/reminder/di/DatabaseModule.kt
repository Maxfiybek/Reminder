package developer.maxfiybek.reminder.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import developer.maxfiybek.reminder.data.db.ReminderDatabase
import developer.maxfiybek.reminder.utils.Constants.FileName.ROOM_DB_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @[Provides Singleton]
    fun provideRoomDatabase(@ApplicationContext context: Context): ReminderDatabase {
        return Room.databaseBuilder(
            context = context, ReminderDatabase::class.java,
            ROOM_DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @[Provides Singleton]
    fun provideReminderDao(database: ReminderDatabase) = database.reminderDao()
}