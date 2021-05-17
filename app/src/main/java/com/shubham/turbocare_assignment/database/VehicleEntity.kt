package com.shubham.turbocare_assignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "vehicle_details")
class VehicleEntity(
    @PrimaryKey   val Reg_no:String,
    @ColumnInfo(name="Vehicle_make") val Vehicle_make: String,
    @ColumnInfo(name="Vehicle_model") val Vehicle_model:String,
    @ColumnInfo(name="Vehicle_fuel_type") val Vehicle_fuel_type: String,
    @ColumnInfo(name="Vehicle_transmission") val Vehicle_transmission: String) {
}