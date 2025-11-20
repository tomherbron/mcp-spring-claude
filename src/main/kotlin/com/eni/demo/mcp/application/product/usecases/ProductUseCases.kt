package com.eni.demo.mcp.application.product.usecases

import com.eni.demo.mcp.application.product.commands.CreateProductCommand
import com.eni.demo.mcp.application.product.commands.UpdateProductCommand
import com.eni.demo.mcp.domain.annotations.PrimaryPort
import com.eni.demo.mcp.domain.product.model.Product
import com.eni.demo.mcp.domain.product.model.ProductId

/**
 * Primary port interface for product use cases.
 * Defines operations related to product management.
 */
@PrimaryPort
interface ProductUseCases {
    fun findAll(): List<Product>
    fun getById(productId: ProductId): Product?
    fun create(command: CreateProductCommand): Product
    fun createBulk(commands: List<CreateProductCommand>): List<Product>
    fun update(command: UpdateProductCommand): Product
    fun delete(productId: ProductId)
}