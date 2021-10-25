package com.example.calculatelistcard

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.calculatelistcard.databinding.ComponentListFoodBinding
import com.example.calculatelistcard.databinding.FragmentFoodBinding

@EpoxyModelClass(layout = R.layout.component_list_food)
abstract class FoodModel : EpoxyModelWithHolder<FoodModel.FoodHolder>() {

    @field:EpoxyAttribute
    var food : String? = null

    @field:EpoxyAttribute
    var result : String? = null

    @field:EpoxyAttribute
    lateinit var minusClickListener : () -> Unit

    @field:EpoxyAttribute
    lateinit var plusClickListener : () -> Unit

    override fun bind(holder: FoodHolder) {
        holder.binding.foodTextView.text = food
        holder.binding.resultTextView.text = result
        holder.binding.minusBottom.setOnClickListener {
            minusClickListener()
        }

        holder.binding.plusBottom.setOnClickListener {
            plusClickListener()
        }
    }

    class FoodHolder : EpoxyHolder(){
        lateinit var binding: ComponentListFoodBinding
        private set

        override fun bindView(itemView: View) {
            binding = ComponentListFoodBinding.bind(itemView)
        }

    }
}