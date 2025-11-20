package com.eni.demo.mcp.application.cart.service

import com.eni.demo.mcp.application.cart.commands.AddProductToCartCommand
import com.eni.demo.mcp.application.cart.commands.RemoveProductFromCartCommand
import com.eni.demo.mcp.application.cart.usecases.CartUseCases
import com.eni.demo.mcp.domain.cart.model.Cart
import com.eni.demo.mcp.domain.cart.model.CartId
import com.eni.demo.mcp.domain.cart.spi.CartRepository
import com.eni.demo.mcp.domain.product.spi.ProductRepository
import org.springframework.stereotype.Service


/**
 * * Service implementation for cart use cases.
 * Implements operations defined in the CartUseCases interface.
 * @param cartRepository The repository for cart persistence operations.
 * @param productRepository The repository for product persistence operations.
 */
@Service
class CartService(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) : CartUseCases {
    override fun getById(cartId: CartId): Cart {
        return cartRepository.getById(cartId) ?:
        throw IllegalArgumentException("Cart not found")
    }

    override fun create(): Cart {
        val newCart = Cart.Companion.createEmptyCart()
        return cartRepository.create(newCart)
    }

    override fun addProductToCart(
        command: AddProductToCartCommand
    ): Cart {
        val cart = cartRepository.getById(command.cartId)
            ?: throw IllegalArgumentException("Cart not found")

        val product = productRepository.getById(command.productId)
            ?: throw IllegalArgumentException("Product not found")

        cart.addProduct(product, command.quantity)
        return cartRepository.update(cart)
    }

    override fun removeProductFromCart(
        command: RemoveProductFromCartCommand
    ): Cart {
        val cart = cartRepository.getById(command.cartId)
            ?: throw IllegalArgumentException("Cart not found")

        val product = productRepository.getById(command.productId)
            ?: throw IllegalArgumentException("Product not found")

        cart.removeProduct(product)
        return cartRepository.update(cart)
    }
}