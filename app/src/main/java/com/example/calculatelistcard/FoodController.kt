package com.example.calculatelistcard

import com.airbnb.epoxy.EpoxyController
import java.util.concurrent.CopyOnWriteArrayList

class FoodController(private val itemClickListener: (Int) -> Unit) : EpoxyController() {

    private var foods: CopyOnWriteArrayList<Food> = CopyOnWriteArrayList()

    fun submitData(list: ArrayList<Food>) {
        foods.clear()
        foods.addAll(list)
        requestModelBuild()
    }

    override fun buildModels() {itemClickListener

        foods.forEachIndexed { index, foodItem ->
            food {
                id(foodItem.id)
                food(foodItem.food)
                result(foodItem.amount.toString())
                minusClickListener {
                    this@FoodController.itemClickListener(foodItem.id!!.toInt())
                    if (foodItem.amount != 0) {
                        this@FoodController.foods[index].amount = foodItem.amount?.minus(1)
                        this@FoodController.requestModelBuild()
                    }
                }

                plusClickListener {
                    this@FoodController.itemClickListener(foodItem.id!!.toInt())
                    this@FoodController.foods[index].amount = foodItem.amount?.plus(1)
                    this@FoodController.requestModelBuild()
                }
            }
        }
    }

    fun sumItem() {

    }
}