package com.eni.demo.mcp.infrastructure.mcp.claude.adapters

import com.eni.demo.mcp.application.cart.usecases.CartUseCases
import com.eni.demo.mcp.domain.cart.model.Cart
import com.eni.demo.mcp.infrastructure.mcp.models.AddProductToCartRequestModel
import com.eni.demo.mcp.infrastructure.mcp.models.GetCartRequestModel
import com.eni.demo.mcp.infrastructure.mcp.models.RemoveProductFromCartRequestModel
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * Adapter for Cart operations using MCP services.
 */
@Component
class CartMcpAdapter(
    private val cartUseCases: CartUseCases,
) {
    @Tool(name = "get_cart_by_id", description = "Retrieve a cart by its ID")
    @Transactional(readOnly = true)
    fun getCartById(
        @ToolParam(description = "Request containing cart UUID (string)") request: GetCartRequestModel
    ): Cart {
        return cartUseCases.getById(request.toCartId())
    }

    @Tool(name = "create_cart", description = "Create a new empty cart")
    @Transactional
    fun createCart(): Cart {
        return cartUseCases.create()
    }

    @Tool(name = "add_product_to_cart", description = "Add a product to a cart")
    @Transactional
    fun addProduct(
        @ToolParam(description = "Request containing cartId (string), productId (UUID string), quantity (int)")
        request: AddProductToCartRequestModel
    ): Cart {
        val command = request.toCommand()
        return cartUseCases.addProductToCart(command)
    }

    @Tool(name = "remove_product_from_cart", description = "Remove a product from a cart")
    @Transactional
    fun removeProduct(
        @ToolParam(description = "Request containing cartId (string), productId (string)") request: RemoveProductFromCartRequestModel
    ): Cart {
        val command = request.toCommand()
        return cartUseCases.removeProductFromCart(command)
    }
}