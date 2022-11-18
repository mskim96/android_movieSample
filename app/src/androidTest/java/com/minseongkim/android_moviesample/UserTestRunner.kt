package com.minseongkim.android_moviesample

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnitRunner
import com.minseongkim.android_moviesample.data.db.UserDao
import com.minseongkim.android_moviesample.data.db.UserDatabase
import com.minseongkim.android_moviesample.data.model.UserEntity
import com.minseongkim.android_moviesample.presentation.Hilt.DataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


class UserTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context, UserDatabase::class.java
    ).allowMainThreadQueries().build()
}

@HiltAndroidTest
@SmallTest
class UserDaoTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: UserDatabase
    private lateinit var userDao: UserDao


    @Before
    fun setUp() {
        hiltRule.inject()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }
}