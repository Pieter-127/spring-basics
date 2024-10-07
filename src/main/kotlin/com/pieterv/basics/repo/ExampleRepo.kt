package com.pieterv.basics.repo

import com.pieterv.basics.domain.ExampleData

interface ExampleRepo {
    fun getListOfData(): List<ExampleData>
    fun getData(id: String): ExampleData
    fun updateData(item: ExampleData): ExampleData
    fun addData(item: ExampleData): ExampleData
    fun deleteData(id: String): Boolean
}