package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.FuelRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelRecordDao {

    @Query("SELECT * FROM fuel_records ORDER BY odometer DESC, date DESC")
    fun getAllRecords(): Flow<List<FuelRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: FuelRecord): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(records: List<FuelRecord>)

    @Update
    suspend fun updateRecord(record: FuelRecord)

    @Delete
    suspend fun deleteRecord(record: FuelRecord)

    @Query("DELETE FROM fuel_records WHERE id = :id")
    suspend fun deleteRecordById(id: Int)

    @Query("DELETE FROM fuel_records")
    suspend fun deleteAllRecords()
}
