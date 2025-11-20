package com.eni.demo.mcp.application.product.service

import com.eni.demo.mcp.application.product.commands.CreateProductCommand
import com.eni.demo.mcp.application.product.commands.UpdateProductCommand
import com.eni.demo.mcp.application.product.usecases.ProductUseCases
import com.eni.demo.mcp.domain.product.model.Product
import com.eni.demo.mcp.domain.product.model.ProductId
import com.eni.demo.mcp.domain.product.spi.ProductRepository
import org.springframework.stereotype.Service

/**
 * Service implementation for product use cases.
 * Handles business logic related to product management.
 * @param productRepository The repository for product persistence operations.
 */
@Service
class ProductService(
    private val productRepository: ProductRepository,
): ProductUseCases {
    override fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    override fun getById(productId: ProductId): Product {
        return productRepository.getById(productId)
            ?: throw IllegalArgumentException("Product not found")
    }

    override fun create(command: CreateProductCommand): Product {
        val newProduct = Product.createFrom(
            name = command.name,
            description = command.description,
            price = command.price,
            stockQuantity = command.stockQuantity
        )

        return productRepository.create(newProduct)
    }

    override fun createBulk(commands: List<CreateProductCommand>): List<Product> {
        val products = mutableListOf<Product>()
        for (command in commands) {
            products.add(
                Product.createFrom(
                    name = command.name,
                    description = command.description,
                    price = command.price,
                    stockQuantity = command.stockQuantity
                )
            )
        }
        return productRepository.createBulk(products)
    }

    override fun update(command: UpdateProductCommand): Product {
        val existingProduct = productRepository.getById(command.id)
            ?: throw IllegalArgumentException("Product not found")

        val updatedProduct = existingProduct.let {
            var prod = it
            command.name?.let { newName -> prod = prod.withNewName(newName) }
            command.description?.let { newDescription -> prod = prod.withNewDescription(newDescription) }
            command.price?.let { newPrice -> prod = prod.withNewPrice(newPrice) }
            command.stock?.let { newStock -> prod = prod.withNewStockQuantity(newStock) }
            prod
        }

        return productRepository.update(updatedProduct)
    }

    override fun delete(productId: ProductId) {
        val existingProduct = productRepository.getById(productId)
            ?: throw IllegalArgumentException("Product not found")

        productRepository.deleteById(existingProduct.id)
    }
}