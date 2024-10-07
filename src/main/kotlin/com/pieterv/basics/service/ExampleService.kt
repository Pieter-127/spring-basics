package com.pieterv.basics.service

import com.pieterv.basics.domain.ExampleData

interface ExampleService {
    fun getListOfItems(): List<ExampleData>
    fun getItem(id: String): ExampleData
    fun updateItem(update: ExampleData): ExampleData
    fun addItem(data: ExampleData): ExampleData
    fun deleteItem(key: String): Boolean
}