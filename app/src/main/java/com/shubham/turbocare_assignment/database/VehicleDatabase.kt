package com.shubham.turbocare_assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VehicleEntity::class], version = 1,exportSchema = false)
abstract class VehicleDatabase:RoomDatabase()  {

   abstract fun getVehicleDao(): VehicleDao

}