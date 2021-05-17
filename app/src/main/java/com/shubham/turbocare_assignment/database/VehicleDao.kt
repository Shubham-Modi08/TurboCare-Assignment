package com.shubham.turbocare_assignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VehicleDao {

        @Insert
        fun insertVehicleDetails(VehicleEntity:VehicleEntity)

        @Query("Select * from vehicle_details order by Reg_no ASC")
        fun getMyVehicles(): List<VehicleEntity>
}