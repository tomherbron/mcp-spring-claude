package com.eni.demo.mcp.infrastructure.mcp.claude.adapters

import com.eni.demo.mcp.application.product.usecases.ProductUseCases
import com.eni.demo.mcp.domain.product.model.Product
import com.eni.demo.mcp.infrastructure.mcp.models.CreateProductBulkRequestModel
import com.eni.demo.mcp.infrastructure.mcp.models.CreateProductRequestModel
import com.eni.demo.mcp.infrastructure.mcp.models.GetProductRequestModel
import com.eni.demo.mcp.infrastructure.mcp.models.UpdateProductRequestModel
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Component

/**
 * Adapter for Product operations using MCP services.
 */
@Component
class ProductMcpAdapter(
    private val productUseCases: ProductUseCases
) {
    @Tool(
        name = "find_all_products",
        description = "Retrieve all products available in the catalog"
    )
    fun findAll(): List<Product> {
        return productUseCases.findAll()
    }

    @Tool(
        name = "get_product_by_id",
        description = "Retrieve a product by its unique identifier"
    )
    fun getProductById(
        @ToolParam(description = "Request containing the product UUID (string)")
        request: GetProductRequestModel): Product? {
        return productUseCases.getById(request.toProductId())
    }

    @Tool(
        name = "create_product",
        description = "Create a new product in the catalog"
    )
    fun createProduct(
        @ToolParam(description = "Product to be created") request: CreateProductRequestModel
    ): Product {
        val command = request.toCommand()
        return productUseCases.create(command)
    }

    @Tool(
        name = "create_products_batch",
        description = "Create multiple products in the catalog"
    )
    fun createProductsBulk(
        @ToolParam(description = "List of products to be created") request: CreateProductBulkRequestModel
    ): List<Product> {
        val commands = request.toCommands()
        return productUseCases.createBulk(commands)
    }

    @Tool(
        name = "update_product",
        description = "Update an existing product in the catalog"
    )
    fun updateProduct(
        @ToolParam(description = "Product to be updated") request: UpdateProductRequestModel
    ): Product {
        val command = request.toCommand()
        return productUseCases.update(command)
    }

    @Tool(
        name = "delete_product_by_id",
        description = "Delete a product from the catalog by its unique identifier"
    )
    fun deleteProductById(
        @ToolParam(description = "Request containing the product UUID (string)") request: GetProductRequestModel
    ) {
        productUseCases.delete(request.toProductId())
    }
}