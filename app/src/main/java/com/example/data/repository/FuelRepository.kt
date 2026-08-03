package com.example.data.repository

import com.example.data.db.FuelRecordDao
import com.example.data.model.FuelRecord
import kotlinx.coroutines.flow.Flow

class FuelRepository(private val dao: FuelRecordDao) {

    val allRecords: Flow<List<FuelRecord>> = dao.getAllRecords()

    suspend fun insert(record: FuelRecord): Long {
        return dao.insertRecord(record)
    }

    suspend fun insertAll(records: List<FuelRecord>) {
        dao.insertAll(records)
    }

    suspend fun update(record: FuelRecord) {
        dao.updateRecord(record)
    }

    suspend fun delete(record: FuelRecord) {
        dao.deleteRecord(record)
    }

    suspend fun deleteById(id: Int) {
        dao.deleteRecordById(id)
    }

    suspend fun deleteAll() {
        dao.deleteAllRecords()
    }
}
