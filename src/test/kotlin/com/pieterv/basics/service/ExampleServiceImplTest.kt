package com.pieterv.basics.service

import com.pieterv.basics.domain.ExampleData
import com.pieterv.basics.repo.ExampleRepo
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class ExampleServiceImplTest {

    private lateinit var systemUnderTest: ExampleService

    private val mockApiService = mock<ExampleRepo>()

    @BeforeEach
    fun setUp() {
        systemUnderTest = ExampleServiceImpl(
            mockApiService
        )
    }

    @Test
    fun whenGettingListOfItems_thenRequestsListFromRepository() {
        systemUnderTest.getListOfItems()
        verify(mockApiService).getListOfData()
    }

    @Test
    fun whenGettingSingleItem_thenRequestsItemFromRepository() {
        systemUnderTest.getItem("1")
        verify(mockApiService).getData("1")
    }

    @Test
    fun whenUpdatingItem_thenRequestsUpdateFromRepository() {
        val newData = ExampleData("3", "new value")
        systemUnderTest.updateItem(newData)
        verify(mockApiService).updateData(newData)
    }

    @Test
    fun whenAddingItem_thenRequestsAddFromRepository() {
        val newData = ExampleData("3", "new value")
        systemUnderTest.addItem(newData)
        verify(mockApiService).addData(newData)
    }

    @Test
    fun whenDeletingItem_thenRequestsDeleteFromRepository() {
        systemUnderTest.deleteItem("1")
        verify(mockApiService).deleteData("1")
    }

}