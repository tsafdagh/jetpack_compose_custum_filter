package com.biometric.pdfviewer.persistence.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.biometric.pdfviewer.persistence.FacilityEntity
import com.biometric.pdfviewer.persistence.dao.FacilityEntityDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.*
import org.junit.runner.RunWith
import java.util.*

//https://blog.devgenius.io/testing-room-database-with-coroutines-and-flows-testing-fundamentals-iii-5f6c3b9e4c94

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class ItemDaoTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var itemDao: FacilityEntityDao
    private lateinit var itemDb: AppDatabase

    @Before
    fun create() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        itemDb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()

        itemDao = itemDb.facilityDao()
    }

    @After
    fun cleanup() {
        itemDb.close()
    }

    @Test
    fun addItem_shouldReturn_theItem_inFlow() = runTest {
        val item1 = FacilityEntity(facilityId="1", facilityName="name1", hiringOrgId=UUID.randomUUID().toString(), hiringOrgLogo="", hiringOrgName="", isCompliant=false)
        val item2 =FacilityEntity(facilityId="2", facilityName="name2", hiringOrgId=UUID.randomUUID().toString(), hiringOrgLogo="", hiringOrgName="", isCompliant=false)

        itemDao.insert(item1)
        itemDao.insert(item2)

        itemDao.getAllEntities().test {
            val list = awaitItem()
            assert(list.contains(item1))
            assert(list.contains(item2))
            cancel()
        }
    }

    @Test
    fun deletedItem_shouldNot_be_present_inFlow() = runTest {
        val item1 = FacilityEntity(facilityId="1", facilityName="name1", hiringOrgId=UUID.randomUUID().toString(), hiringOrgLogo="", hiringOrgName="", isCompliant=false)
        val item2 =FacilityEntity(facilityId="2", facilityName="name2", hiringOrgId=UUID.randomUUID().toString(), hiringOrgLogo="", hiringOrgName="", isCompliant=false)

        itemDao.insert(item1)
        itemDao.insert(item2)
        itemDao.deleteById(item2.facilityId)
        itemDao.getAllEntities().test {
            val list = awaitItem()
            assert(list.size == 1)
            assert(list.contains(item1))
            cancel()
        }
    }


    @Test
    fun updateItem_shouldReturn_theItem_inFlow() = runTest {
        val item1 = FacilityEntity(facilityId="1", facilityName="name1", hiringOrgId=UUID.randomUUID().toString(), hiringOrgLogo="", hiringOrgName="", isCompliant=false)
        val item2 =FacilityEntity(facilityId="2", facilityName="name2", hiringOrgId=UUID.randomUUID().toString(), hiringOrgLogo="", hiringOrgName="", isCompliant=false)

        val item3 = FacilityEntity(facilityId="2", facilityName="name2", hiringOrgId=UUID.randomUUID().toString(), hiringOrgLogo="", hiringOrgName="", isCompliant=false)

        itemDao.insert(item1)
        itemDao.insert(item2)
        itemDao.insertReplace(item3)
        itemDao.getAllEntities().test {
            val list = awaitItem()
            assert(list.size == 2)
            assert(list.contains(item3))
            cancel()
        }
    }
}