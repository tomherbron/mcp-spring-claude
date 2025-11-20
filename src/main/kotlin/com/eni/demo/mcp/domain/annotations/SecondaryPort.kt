package com.eni.demo.mcp.domain.annotations

/**
 * Annotation to mark classes that represent secondary ports in the domain.
 * Secondary ports are interfaces that define the operations available for use by the domain layer.
 * They are typically used to interact with external systems or services, such as repositories or external APIs.
 *
 * Interfaces are created in the `domain` module, but they are implemented in the `infrastructure` module.
 */
annotation class SecondaryPort()