package com.pieterv.basics.service

import com.pieterv.basics.domain.ExampleData
import com.pieterv.basics.repo.ExampleRepo
import org.springframework.stereotype.Service

@Service
class ExampleServiceImpl(private val exampleRepo: ExampleRepo) : ExampleService {

    override fun getListOfItems(): List<ExampleData> {
        return exampleRepo.getListOfData()
    }

    override fun getItem(id: String): ExampleData {
        return exampleRepo.getData(id)
    }

    override fun updateItem(update: ExampleData): ExampleData {
        return exampleRepo.updateData(update)
    }


    override fun addItem(data: ExampleData): ExampleData {
        exampleRepo.addData(data)
        return data
    }

    override fun deleteItem(key: String): Boolean {
        return exampleRepo.deleteData(key)
    }
}