package com.example.newproject.model.model

data class NumberModel(
    val number : Int
) {
    fun getColor(){
        when(number){
            in(1..5) -> {

            }

        }
    }
}