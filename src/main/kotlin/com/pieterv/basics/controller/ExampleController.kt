package com.pieterv.basics.controller

import com.pieterv.basics.domain.ExampleData
import com.pieterv.basics.service.ExampleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item")
class ExampleController(val exampleService: ExampleService) {

    @GetMapping
    fun getItems(): List<ExampleData> {
        return exampleService.getListOfItems()
    }

    @GetMapping("{id}")
    fun getItem(@PathVariable id: String): ExampleData {
        return exampleService.getItem(id)
    }

    @PutMapping
    fun updateItem(@RequestBody data: ExampleData): ExampleData {
        return exampleService.updateItem(data)
    }

    @PostMapping
    fun createItem(@RequestBody data: ExampleData): ExampleData {
        return exampleService.addItem(data)
    }

    @DeleteMapping("{id}")
    fun deleteItem(@PathVariable id: String): Boolean {
        return exampleService.deleteItem(id)
    }

}