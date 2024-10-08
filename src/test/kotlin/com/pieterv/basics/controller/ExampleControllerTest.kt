package com.pieterv.basics.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.pieterv.basics.domain.ExampleData
import com.pieterv.basics.service.ExampleService
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ExampleController::class)
class ExampleControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var exampleService: ExampleService

    @Test
    fun whenGetItems_thenReturnsListOfItems() {
        val exampleDataList = listOf(ExampleData("1", "item1"))
        `when`(exampleService.getListOfItems()).thenReturn(exampleDataList)

        mockMvc.perform(get("/item"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[0].name").value("item1"))

        verify(exampleService).getListOfItems()
    }

    @Test
    fun whenGetItem_thenReturnsSingleItem() {
        val exampleData = ExampleData("1", "item1")
        `when`(exampleService.getItem("1")).thenReturn(exampleData)

        mockMvc.perform(get("/item/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("item1"))

        verify(exampleService).getItem("1")
    }

    @Test
    fun whenCreateItem_thenReturnsCreatedItem() {
        val newItem = ExampleData("3", "new item")
        `when`(exampleService.addItem(newItem)).thenReturn(newItem)

        val requestBody = objectMapper.writeValueAsString(ExampleData("3", "new item"))

        mockMvc.perform(
            post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value("3"))
            .andExpect(jsonPath("$.name").value("new item"))

        verify(exampleService).addItem(newItem)
    }

    @Test
    fun whenUpdateItem_thenReturnsUpdatedItem() {
        val updatedItem = ExampleData("1", "updated item")
        `when`(exampleService.updateItem(updatedItem)).thenReturn(updatedItem)

        val requestBody = objectMapper.writeValueAsString(ExampleData("1", "updated item"))

        mockMvc.perform(
            put("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("updated item"))

        verify(exampleService).updateItem(updatedItem)
    }

    @Test
    fun whenDeleteItem_thenReturnsTrue() {
        `when`(exampleService.deleteItem("1")).thenReturn(true)

        mockMvc.perform(delete("/item/1"))
            .andExpect(status().isOk)
            .andExpect(content().string("true"))

        verify(exampleService).deleteItem("1")
    }
}
