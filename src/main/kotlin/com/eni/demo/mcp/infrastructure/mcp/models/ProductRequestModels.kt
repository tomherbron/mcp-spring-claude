package com.eni.demo.mcp.infrastructure.mcp.models

import com.eni.demo.mcp.application.product.commands.CreateProductCommand
import com.eni.demo.mcp.application.product.commands.UpdateProductCommand
import com.eni.demo.mcp.domain.product.model.ProductId
import java.util.UUID

data class CreateProductRequestModel(
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int
) {
    fun toCommand(): CreateProductCommand {
        return CreateProductCommand(
            name = name,
            description = description,
            price = price,
            stockQuantity = stockQuantity
        )
    }
}

data class CreateProductBulkRequestModel(
    val products: List<CreateProductRequestModel>
) {
    fun toCommands(): List<CreateProductCommand> {
        return products.map { it.toCommand() }
    }
}

data class UpdateProductRequestModel(
    val id: String,
    val name: String?,
    val description: String?,
    val price: Double?,
    val stock: Int?
) {
    fun toCommand(): UpdateProductCommand {
        return UpdateProductCommand(
            id = ProductId(UUID.fromString(id)),
            name = name,
            description = description,
            price = price,
            stock = stock
        )
    }
}

data class DeleteProductRequestModel(
    val id: String
) {
    fun toProductId(): ProductId {
        return ProductId(UUID.fromString(id))
    }
}

data class GetProductRequestModel(
    val id: String
) {
    fun toProductId(): ProductId {
        return ProductId(UUID.fromString(id))
    }
}