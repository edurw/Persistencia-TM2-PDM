package com.works.odev8.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,

    val groupName : String?,
    val name : String?,
    val categoria: String?,
    val phone : String?
    )
