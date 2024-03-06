package com.abbosidev

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.RestResponse.StatusCode.BAD_REQUEST

@Path("/api/v1/user")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class UserResource(private val userService: UserService) {

    @POST
    fun register(user: UserDto): Response {
        val saved = userService.saveNewUser(user)
        if (saved == null) {
            val message = HashMap<String, String>().apply {
                put("message", "User already exists")
            }
            println(message)
            return Response
                .status(BAD_REQUEST)
                .entity(message)
                .build()
        }
        return Response.ok(saved).build()
    }

}