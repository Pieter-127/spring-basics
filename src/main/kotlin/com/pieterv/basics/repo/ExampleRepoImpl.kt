package com.pieterv.basics.repo

import com.pieterv.basics.domain.ExampleData
import org.springframework.stereotype.Repository

@Repository
class ExampleRepoImpl : ExampleRepo {

    override fun getListOfData(): List<ExampleData> {
        return listOfData
    }

    override fun getData(id: String): ExampleData {
        return listOfData.firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Item with key $id not found")
    }

    override fun updateData(item: ExampleData): ExampleData {
        val index = listOfData.indexOfFirst {
            it.id == item.id
        }
        if (index != -1) {
            listOfData[index] = listOfData[index].copy(name = item.name)
        } else {
            throw NoSuchElementException("Item not found")
        }
        return item
    }

    override fun addData(item: ExampleData): ExampleData {
        if (listOfData.any { it.id == item.id }) {
            throw IllegalArgumentException("Item already exists")
        }
        listOfData.add(item)
        return item
    }

    override fun deleteData(id: String): Boolean {
        return listOfData.removeIf {
            it.id == id
        }
    }

    private companion object {
        val listOfData = arrayListOf(ExampleData("1", "hello"), ExampleData("2", "world"))
    }
}