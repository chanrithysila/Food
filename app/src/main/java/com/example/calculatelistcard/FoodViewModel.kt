package com.example.calculatelistcard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {
    val foodLists: MutableLiveData<ArrayList<Food>> = MutableLiveData(ArrayList())
    private val temFoods : ArrayList<Food> = ArrayList()
    init {
        if(foodLists.value?.isEmpty() ==true) {
            List(10){
                temFoods.add(
                    Food(
                        id = it,
                        food = "Pizza $it",
                        amount = 0
                    )
                )
            }
            foodLists.postValue(temFoods)
        }

    }

    fun refreshData(){
        foodLists.postValue(temFoods)
    }
    fun search(id: Int) {
        val searchTemList: ArrayList<Food> = ArrayList()
                val searchedItem = temFoods.find { item -> item.id == id }
                searchedItem?.let { searchTemList.add(it) }
        foodLists.postValue(searchTemList)
    }
}