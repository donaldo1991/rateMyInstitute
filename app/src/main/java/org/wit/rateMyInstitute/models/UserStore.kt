package org.wit.rateMyInstitute.models

interface UserStore {
    fun findAll(): List<UserModel>
    fun create(rating: UserModel)
    fun delete(rating: UserModel)
}