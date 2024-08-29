package developer.maxfiybek.reminder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import developer.maxfiybek.reminder.data.db.dao.ReminderDao
import developer.maxfiybek.reminder.data.repository.MainScreenRepoImpl
import developer.maxfiybek.reminder.data.repository.MainScreenRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @[Provides Singleton]
    fun provideMainScreenRepository(dao: ReminderDao): MainScreenRepository =
        MainScreenRepoImpl(dao)
}