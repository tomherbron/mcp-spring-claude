package com.eni.demo.mcp.domain.cart.spi

import com.eni.demo.mcp.domain.annotations.SecondaryPort
import com.eni.demo.mcp.domain.cart.model.Cart
import com.eni.demo.mcp.domain.cart.model.CartId

/**
 * Cart client interface for interacting with cart data sources.
 */
@SecondaryPort
interface CartRepository {
    fun findAll(): List<Cart>
    fun getById(id: CartId): Cart?
    fun create(cart: Cart): Cart
    fun update(cart: Cart): Cart
    fun deleteById(id: CartId)
}