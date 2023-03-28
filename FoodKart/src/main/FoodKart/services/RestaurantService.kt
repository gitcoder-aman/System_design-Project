package services

import data.Dao
import exception.CustomException
import model.RestaurantModel
import model.ReviewModel

class RestaurantService {
    private fun RestaurantService(){

    }
    companion object{
        @Volatile private var instance: RestaurantService? = null

        fun getInstance(): RestaurantService {
            if(instance == null){
                synchronized(this){
                    if(instance == null){
                        instance = RestaurantService()
                    }
                }
            }
            return instance!!
        }
    }
    private val restaurantDao:Dao = Dao.getInstance()

    public fun registerRestaurant(restaurantName:String,pinCodes:String,item:String,price:Int,quantity:Int):RestaurantModel?{
        //now checking the  data
        if(price < 0 && quantity < 0 ){
            return throw CustomException("Invalid Value for mandatory fields")
        }else if(restaurantName.isEmpty()){
            return throw CustomException("Restaurant name is Invalid")
        }
        return restaurantDao.registerRestaurant(restaurantName,pinCodes,item,price,quantity)
    }
    //update quantity
    public fun updateQuantity(restaurantName: String,quantity: Int): RestaurantModel? {
        //check the data
        if(restaurantName == null || quantity < 0){
            return throw CustomException("Enter value for mandatory fields")
        }
        return restaurantDao.updateQuantity(restaurantName, quantity)
    }
    //rate restaurant
    public fun rateRestaurant(restaurantName: String,rating:Float,comment:String):ReviewModel{
        //now check the data
        if(restaurantName == null || rating <= 0 || comment == null){
            return throw CustomException("Invalid values for mandatory fields")
        }
        return restaurantDao.rateRestaurant(restaurantName,rating,comment)
    }
    public fun showListOfRestaurant(sortBy:String):List<RestaurantModel>?{
        if(sortBy == "rating" || sortBy == "price"){
            return restaurantDao.showListOfRestaurant(sortBy)
        }
        return throw CustomException("only Sort by Rating/Price")
    }
}