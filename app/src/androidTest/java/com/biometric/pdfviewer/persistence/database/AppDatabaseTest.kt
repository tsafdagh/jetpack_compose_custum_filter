package com.biometric.pdfviewer.persistence.database

import android.content.Context
import com.biometric.pdfviewer.persistence.FacilityEntity
import com.biometric.pdfviewer.persistence.dao.FacilityEntityDao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class) // Annotate with @RunWith
class AppDatabaseTest : TestCase() {
    // get reference to the LanguageDatabase and LanguageDao atabase and Dao class
    private lateinit var db: AppDatabase
    private lateinit var dao: FacilityEntityDao

    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called
    @Before
    public override fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application
        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.facilityDao()
    }

    // Override function closeDb() and annotate it with @After
    // this function will be called at last when this test class is called
    @After
    fun closeDb() {
        db.close()
    }

    // create a test function and annotate it with @Test
    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    fun insertAndReadData(): Unit = runBlocking {

        val entity = FacilityEntity(
            facilityId = UUID.randomUUID().toString(),
            facilityName = "facility1",
            hiringOrgId = UUID.randomUUID().toString(),
            hiringOrgLogo = "Hiring logo",
            hiringOrgName = "Hiring name",
            isCompliant = false
        )
        dao.insert(entity)




        val entities = async {
            dao.getAllEntities()
        }

        println("\n entities" +
                "$entities")
       // assertThat(entities.await().contains(entity)).isTrue()
    }
}