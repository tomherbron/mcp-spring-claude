package com.eni.demo.mcp.application.cart.commands

import com.eni.demo.mcp.domain.product.model.ProductId
import com.eni.demo.mcp.domain.cart.model.CartId

data class RemoveProductFromCartCommand(
    val cartId: CartId,
    val productId: ProductId
)
