package com.eni.demo.mcp.infrastructure.jpa.model

import com.eni.demo.mcp.domain.product.model.Product
import com.eni.demo.mcp.domain.cart.model.Cart
import com.eni.demo.mcp.domain.cart.model.CartId
import jakarta.persistence.*
import java.util.*

/**
 * JPA Entity representing a shopping cart
 * @param id Unique identifier of the cart
 * @param items List of products in the cart
 */
@Entity
@Table(name = "cart")
class CartEntity(
    @Id
    var id: UUID? = null,

    @ManyToMany
    @JoinTable(
        name = "cart_items",
        joinColumns = [JoinColumn(name = "cart_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var items: MutableList<ProductEntity> = mutableListOf()

) {

    fun toDomain(): Cart =
        Cart(
            id = CartId(id ?: UUID.randomUUID()),
            items = items.map { it.toDomain() } as MutableList<Product>
        )

    companion object {
        fun fromDomain(domain: Cart): CartEntity =
            CartEntity(
                id = domain.id.value,
                items = domain.items.map { ProductEntity.fromDomain(it) }.toMutableList()
            )
    }
}