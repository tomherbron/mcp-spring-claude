package com.eni.demo.mcp.application.product.commands

data class CreateProductCommand(
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int
)
