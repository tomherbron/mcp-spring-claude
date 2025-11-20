package com.eni.demo.mcp.application.product.commands

import com.eni.demo.mcp.domain.product.model.ProductId

data class UpdateProductCommand(
    val id: ProductId,
    val name: String?,
    val description: String?,
    val price: Double?,
    val stock: Int?
)
