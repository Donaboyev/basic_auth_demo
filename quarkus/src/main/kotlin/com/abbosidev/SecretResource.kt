package com.abbosidev

import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/api/v1/private")
class SecretResource {

    @RolesAllowed("user")
    @GET
    fun cannotAccessed() = "Cannot accessed"
}