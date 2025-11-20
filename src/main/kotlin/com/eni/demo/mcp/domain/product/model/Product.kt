package com.eni.demo.mcp.domain.product.model

import java.util.UUID

/**
 * Domain model representing a product in the system.
 * @param id Unique identifier for the product.
 * @param name Name of the product.
 * @param description Description of the product.
 * @param price Price of the product.
 * @param stockQuantity Available stock quantity of the product.
 */
data class Product(
    val id: ProductId,
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int
) {
    fun withNewName(newName: String) = this.copy(name = newName)
    fun withNewDescription(newDescription: String) = this.copy(description = newDescription)
    fun withNewPrice(newPrice: Double) = this.copy(price = newPrice)
    fun withNewStockQuantity(newStockQuantity: Int) = this.copy(stockQuantity = newStockQuantity)

    companion object {
        fun createFrom(
            name: String,
            description: String,
            price: Double,
            stockQuantity: Int
        ): Product {

            if (name.isBlank()) {
                throw IllegalArgumentException("Product name cannot be blank")
            }

            if (price < 0) {
                throw IllegalArgumentException("Product price cannot be negative")
            }

            if (stockQuantity < 0) {
                throw IllegalArgumentException("Stock quantity cannot be negative")
            }

            return Product(
                id = ProductId(UUID.randomUUID()),
                name = name,
                description = description,
                price = price,
                stockQuantity = stockQuantity
            )
        }
    }
}