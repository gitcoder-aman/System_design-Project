package model

import constants.enumGender

class UserModel(id: Int, phoneNumber: Long, userName: String, pinCode: Long, gender: enumGender) {

     var userId:Int?=id
        get() = field
        set(value){
            field = value
        }
     var userName:String?=userName
        get() = field
        set(value){
            field = value
        }
     var gender: enumGender?=gender
        get() = field
        set(value){
            field = value
        }
     var phoneNumber:Long?=phoneNumber
        get() = field
        set(value){
            field = value
        }
     var pinCode:Long?=pinCode
        get() = field
        set(value){
            field = value
        }

     var listRestaurant:ArrayList<RestaurantModel> ?= ArrayList()
        get() = field
        set(value){
            field = value
        }
     var listOrder:ArrayList<OrderModel> ?=ArrayList()
        get() = field
        set(value){
            field = value
        }
}