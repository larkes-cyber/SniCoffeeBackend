package com.example.routes.navigation

sealed class OrderBranch(val route:String) {
    object AddOrderBranch: CoffeeCategoryBranch("/add_order")
    object DeleteOrderBranch: CoffeeCategoryBranch("/delete_order")
    object CompleteOrderBranch: CoffeeCategoryBranch("/complete_order")
    object GetOrdersBranch: CoffeeCategoryBranch("/get_orders")

}