package com.example.pocketinvestor.models


class User {
    private var id: Int? = null


    private var username: String? = null


    private var password: String? = null


    private var email: String? = null


    private var firstname: String? = null


    private var lastname: String? = null

    var roles: Collection<Role>? = null

    constructor()
    constructor(
        username: String?,
        firstname: String?,
        lastname: String?,
        email: String?,
        password: String?
    ) {
        this.username = username
        this.firstname = firstname
        this.lastname = lastname
        this.email = email
        this.password = password
    }
}
