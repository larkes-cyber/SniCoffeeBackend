package com.example.routes.navigation

sealed class OrderBranch(val route:String) {
    object AddOrderBranch: OrderBranch("/add_order")
    object DeleteOrderBranch: OrderBranch("/delete_order")
    object CompleteOrderBranch: OrderBranch("/complete_order")
    object GetOrdersBranch: OrderBranch("/get_orders")
    object GetUserOrders:OrderBranch("/get_user_orders")
}