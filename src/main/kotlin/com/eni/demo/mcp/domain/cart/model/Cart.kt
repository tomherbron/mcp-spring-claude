package com.eni.demo.mcp.domain.cart.model

import com.eni.demo.mcp.domain.product.model.Product
import java.util.UUID

/**
 * Domain model representing a shopping cart that contains products.
 * @param id Unique identifier for the cart.
 * @param items List of products in the cart.
 */
data class Cart(
    val id: CartId,
    val items: MutableList<Product>
) {
    fun addProduct(product: Product, quantity: Int = 1) {
        repeat(quantity) {
            items.add(product)
        }
    }

    fun removeProduct(product: Product, quantity: Int = 1) {
        repeat(quantity){
            if (items.contains(product) && items.count { it == product } >= quantity)
            items.remove(product)
        }
    }

    fun clear() {
        items.clear()
    }

    companion object {
        fun createEmptyCart(): Cart {
            return Cart(
                id = CartId(UUID.randomUUID()),
                items = mutableListOf()
            )
        }
    }
}