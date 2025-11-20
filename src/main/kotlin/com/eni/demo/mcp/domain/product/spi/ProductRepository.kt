package com.eni.demo.mcp.domain.product.spi

import com.eni.demo.mcp.domain.annotations.SecondaryPort
import com.eni.demo.mcp.domain.product.model.Product
import com.eni.demo.mcp.domain.product.model.ProductId

/**
 * Product client interface for interacting with product data sources.
 */
@SecondaryPort
interface ProductRepository {
    fun findAll(): List<Product>
    fun getById(id: ProductId): Product?
    fun create(product: Product): Product
    fun createBulk(products: List<Product>): List<Product>
    fun update(product: Product): Product
    fun deleteById(id: ProductId)
}