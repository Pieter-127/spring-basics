package com.pieterv.basics.repo

import com.pieterv.basics.domain.ExampleData
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

class ExampleRepoImplTest {

    private lateinit var systemUnderTest: ExampleRepo

    @BeforeEach
    fun setUp() {
        systemUnderTest = ExampleRepoImpl()
    }

    @Test
    fun givenNewInstance_whenCreated_thenHasData() {
        assertTrue(
            systemUnderTest.getListOfData()
                .isNotEmpty()
        )
    }

    @Test
    fun givenExistingItem_whenRequestSingleItem_thenReturnsFoundItem() {
        assertDoesNotThrow {
            systemUnderTest.getData("1")
        }
    }

    @Test
    fun givenNonExistingItem_whenRequestSingleItem_thenThrowsErrorNotFound() {
        assertThrows<NoSuchElementException> {
            systemUnderTest.getData("123")
        }
    }

    @Test
    fun givenExistingItem_whenUpdatingWithNewValues_thenUpdatesItem() {
        val expectedResult = ExampleData("1", "new value")
        systemUnderTest.updateData(expectedResult)

        val actualValue = systemUnderTest.getData("1")
        assertEquals(expectedResult.name, actualValue.name)
    }

    @Test
    fun givenNonExistingItem_whenUpdatingWithNewValues_thenThrowsException() {
        assertThrows<NoSuchElementException> {
            systemUnderTest.updateData(ExampleData("", "new values"))
        }
    }

    @Test
    fun givenExistingItemWithSameId_whenAddingNewItem_thenThrowsException() {
        assertThrows<IllegalArgumentException> {
            systemUnderTest.addData(ExampleData("1", "already exists"))
        }
    }

    @Test
    fun givenNewItem_whenAddingItem_thenAddsToList() {
        val expectedResult = ExampleData("3", "new value")
        systemUnderTest.addData(expectedResult)

        val actualValue = systemUnderTest.getData(expectedResult.id)
        assertEquals(expectedResult, actualValue)
    }

    @Test
    fun givenExistingItem_whenDeletingItem_thenRemovesFromList() {
        val initialSize = systemUnderTest.getListOfData().size
        val result = systemUnderTest.deleteData("1")
        val postRemovalSize = systemUnderTest.getListOfData().size
        assertTrue(result)
        assertTrue(initialSize - postRemovalSize == 1)
    }
}