package com.eni.demo.mcp.infrastructure.mcp.models

import com.eni.demo.mcp.application.cart.commands.AddProductToCartCommand
import com.eni.demo.mcp.application.cart.commands.RemoveProductFromCartCommand
import com.eni.demo.mcp.domain.product.model.ProductId
import com.eni.demo.mcp.domain.cart.model.CartId
import java.util.UUID

data class AddProductToCartRequestModel(
    val cartId: String,
    val productId: String,
    val quantity: Int = 1
) {
    fun toCommand(): AddProductToCartCommand {
        return AddProductToCartCommand(
            cartId = CartId(UUID.fromString(cartId)),
            productId = ProductId(UUID.fromString(productId)),
            quantity = quantity
        )
    }
}

data class RemoveProductFromCartRequestModel(
    val cartId: String,
    val productId: String
) {
    fun toCommand(): RemoveProductFromCartCommand {
        return RemoveProductFromCartCommand(
            cartId = CartId(UUID.fromString(cartId)),
            productId = ProductId(UUID.fromString(productId))
        )
    }
}

data class GetCartRequestModel(
    val cartId: String
) {
    fun toCartId(): CartId {
        return CartId(UUID.fromString(cartId))
    }
}