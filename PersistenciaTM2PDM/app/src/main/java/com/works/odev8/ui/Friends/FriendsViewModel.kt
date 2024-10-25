package com.works.odev8.ui.Friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.works.odev8.MainActivity
import com.works.odev8.models.Person

class FriendsViewModel : ViewModel() {

    private val _list = MutableLiveData<List<Person>>()
    val list: LiveData<List<Person>> get() = _list

    var thread: Thread? = null

    init {
        friendsFetchData()
    }

    fun friendsFetchData()
    {
        thread = Thread {
            val db = MainActivity.db
            val data = db.personDao().getSelectedGroupPerson("Friends")
            _list.postValue(data)
        }
        thread?.start()
    }
}