package com.eni.demo.mcp.application.cart.usecases

import com.eni.demo.mcp.application.cart.commands.AddProductToCartCommand
import com.eni.demo.mcp.application.cart.commands.RemoveProductFromCartCommand
import com.eni.demo.mcp.domain.annotations.PrimaryPort
import com.eni.demo.mcp.domain.cart.model.Cart
import com.eni.demo.mcp.domain.cart.model.CartId

/**
 * Primary port interface for cart use cases.
 * Defines operations related to cart management.
 */
@PrimaryPort
interface CartUseCases {
    fun getById(cartId: CartId): Cart
    fun create(): Cart
    fun addProductToCart(command: AddProductToCartCommand): Cart
    fun removeProductFromCart(command: RemoveProductFromCartCommand): Cart
}