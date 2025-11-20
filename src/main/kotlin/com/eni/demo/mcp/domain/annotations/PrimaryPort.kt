package com.eni.demo.mcp.domain.annotations

/**
 * Annotation to mark classes that represent primary ports in the domain.
 * Primary ports are the main entry points for the domain logic and are typically used by application services.
 * They are often interfaces that define the operations available to the application layer.
 *
 * Interfaces are implemented in the `domain` module, and they are used in the `api` module to interact with domain.
 */
annotation class PrimaryPort()