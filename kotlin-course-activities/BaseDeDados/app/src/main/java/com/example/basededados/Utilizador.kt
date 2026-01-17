package com.example.basededados

class Utilizador(val id: Int=0, var username:String="", var password:String = "") {

    override fun toString(): String {
        return "Utilizador: id: $id \n username: $username \npassword: $password)"
    }
}