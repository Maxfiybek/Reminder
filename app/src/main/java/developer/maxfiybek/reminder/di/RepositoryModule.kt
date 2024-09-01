package developer.maxfiybek.reminder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import developer.maxfiybek.reminder.data.db.dao.ReminderDao
import developer.maxfiybek.reminder.data.repository.ReminderRepoImpl
import developer.maxfiybek.reminder.data.repository.ReminderRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @[Provides Singleton]
    fun provideMainScreenRepository(dao: ReminderDao): ReminderRepository =
        ReminderRepoImpl(dao)
}