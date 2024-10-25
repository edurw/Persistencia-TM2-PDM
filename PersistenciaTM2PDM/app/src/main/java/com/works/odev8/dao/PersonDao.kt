package com.works.odev8.dao

import androidx.room.*
import com.works.odev8.models.Person

@Dao
interface PersonDao {

    @Insert
    fun addPerson(person: Person): Long //

    @Query("select * from person") //
    fun getAllPerson(): List<Person>

    @Query("SELECT * FROM person ORDER BY id DESC LIMIT 10")
    fun getLastTenNotes(): List<Person>

    @Query("select * from person where groupName like :groupName ")
    fun getSelectedGroupPerson(groupName: String): List<Person>

    @Query("SELECT * FROM person WHERE groupName LIKE '%' || :search || '%' OR name LIKE '%' || :search  || '%' OR phone LIKE '%' || :search || '%'")
    fun searchByWord(search: String?): List<Person>

    @Query("select * from person where id like :id")
    fun getByID(id: Int): Person

    @Delete
    fun delete(person: Person): Int

    @Update
    fun update(person: Person): Int
}