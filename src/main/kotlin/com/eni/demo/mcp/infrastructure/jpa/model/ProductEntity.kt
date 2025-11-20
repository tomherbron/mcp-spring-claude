package com.eni.demo.mcp.infrastructure.jpa.model

import com.eni.demo.mcp.domain.product.model.Product
import com.eni.demo.mcp.domain.product.model.ProductId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

/**
 * JPA Entity representing a product
 * @param id Unique identifier of the product
 * @param name Name of the product
 * @param description Description of the product
 * @param price Price of the product
 * @param stockQuantity Available stock quantity of the product
 */
@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var description: String = "",

    @Column(nullable = false)
    var price: Double = 0.0,

    @Column(nullable = false)
    var stockQuantity: Int = 0

) {
    fun toDomain(): Product =
        Product(
            id = ProductId(id ?: UUID.randomUUID()), // fallback safety
            name = name,
            description = description,
            price = price,
            stockQuantity = stockQuantity
        )

    companion object {
        fun fromDomain(domain: Product): ProductEntity =
            ProductEntity(
                id = domain.id.value,
                name = domain.name,
                description = domain.description,
                price = domain.price,
                stockQuantity = domain.stockQuantity
            )
    }
}