package data

import constants.enumGender
import exception.CustomException
import model.*
import utils.IdGenerator
import java.util.*
import javax.xml.stream.events.Characters
import javax.xml.stream.events.Comment
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Dao {
    private fun Dao() {

    }

    companion object {
        @Volatile
        private var instance: Dao? = null

        fun getInstance(): Dao {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Dao()
                    }
                }
            }
            return instance!!
        }
    }

    private val userMap: HashMap<Int, UserModel> = HashMap() //userId,userModel
    private val phoneMap: HashMap<Long, Int> = HashMap() //phoneNumber,userId
    private val restaurantNameMap: HashMap<String, RestaurantModel> = HashMap()

    private var loggedInUser: UserModel? = null

    //user register
    public fun registerUser(phoneNumber: Long, userName: String, pinCode: Long, gender: enumGender): UserModel? {
        if (phoneMap.containsKey(phoneNumber)) {
            val userModel: UserModel? = userMap[phoneMap[phoneNumber]]
            println("User already exists with the phone Number " + userModel?.userId)
            return userModel
        }
        //phone number is not contain
        val userModel = UserModel(IdGenerator.getId(), phoneNumber, userName, pinCode, gender)

        phoneMap[phoneNumber] = userModel.userId!!  //put the data in HashMap
        userMap[userModel.userId!!] = userModel

        println("Successful create this user with User Id " + userModel.userId)

        return userModel
    }

    //user Login
    public fun login(userId: Int): UserModel? {
        if (!userMap.containsKey(userId)) {
            return throw CustomException("No User exists with ID $userId")
        }
        val userModel: UserModel? = userMap[userId]
        loggedInUser = userModel

        println("Successful login with this user Id : $userId")
        return userModel
    }

    //register restaurant
    public fun registerRestaurant(
        restaurantName: String,
        pinCodes: String,
        item: String,
        price: Int,
        quantity: Int
    ): RestaurantModel? {

        if (loggedInUser == null) {
            return throw CustomException("No Logged In User found , request can't be served")
        }
        if (restaurantNameMap.containsKey(restaurantName)) {
            return throw CustomException("Restaurants already exists with this given name please give unique name")
        }

        val listOfPinCode: List<String> = pinCodes.split(",").map { it.trim() }

        //check all pinCode are valid now checking
        val pins: ArrayList<Long> = ArrayList()
        if (listOfPinCode.isNotEmpty()) {
            for (s in listOfPinCode) {
                if (!s.all { it.isDigit() }) {
                    return throw CustomException("Invalid PinCode Provides")
                }
                pins.add(s.toLong())
            }
        }

        //use here Builder Pattern
        val restaurantModel: RestaurantModel = RestaurantModel()
        restaurantModel.restaurantId = IdGenerator.getId()
        restaurantModel.restaurantName = restaurantName
        restaurantModel.item = item
        restaurantModel.price = price
        restaurantModel.quantity = quantity
        restaurantModel.listPinCode = pins
        restaurantModel.createdBy = loggedInUser?.userId

        //map the restaurant Name with restaurant Model
        restaurantNameMap[restaurantName] = restaurantModel

        //insert the all listOfRestaurant data in loggedInUser
        loggedInUser?.listRestaurant?.add(restaurantModel)

        println("Successful registered restaurant id " + restaurantModel.restaurantId)
        return restaurantModel
    }

    //update the quantity
    public fun updateQuantity(restaurantName: String, quantity: Int): RestaurantModel? {
        val restaurantModel: RestaurantModel = restaurantNameMap[restaurantName]
            ?: return throw CustomException("No Restaurant found with given name $restaurantName")

        restaurantModel.quantity = restaurantModel.quantity?.plus(quantity)
        println("Quantity have been updated")
        return restaurantModel
    }

    //now rate the restaurant
    public fun rateRestaurant(restaurantName: String, rating: Float, comment: String):ReviewModel{
        val restaurantModel: RestaurantModel = restaurantNameMap[restaurantName]
            ?: return throw CustomException("No Restaurant found with given name $restaurantName")

        val reviewModel: ReviewModel = ReviewModel()
        reviewModel.reviewId = IdGenerator.getId()
        reviewModel.rating = rating
        reviewModel.comment = comment

        //if first review
        if (restaurantModel.listReview.isEmpty()) {
            restaurantModel.rating = rating
        } else {
            //update overall rating just a average calculate
            val currentRating: Float? =
                (restaurantModel.rating?.times(restaurantModel.listReview.size + rating))?.div((restaurantModel.listReview.size + 1))  //just calculate average of rating
            restaurantModel.rating = currentRating
        }
        restaurantModel.listReview.add(reviewModel)
        return reviewModel
    }
    public fun showListOfRestaurant(sortBy:String):List<RestaurantModel>{
        val listRestaurant: ArrayList<RestaurantModel>? = loggedInUser?.listRestaurant
        val newListRestaurant:ArrayList<RestaurantModel> = ArrayList()  //this listOfRestaurant is contains pinCode which is exists pinCode given By Restaurant

        for(restaurantModel:RestaurantModel in listRestaurant!!){
            if(restaurantModel.listPinCode.contains(loggedInUser?.pinCode) && restaurantModel.quantity!! > 0){
                newListRestaurant.add(restaurantModel)
            }
        }
        if(sortBy.equals("rating", ignoreCase = true)){
            Collections.sort(newListRestaurant,SortByRating())

            //print the all data
            for (restaurantModel:RestaurantModel in newListRestaurant){
                println("Restaurant Id: "+restaurantModel.restaurantId + " Restaurant Name: "+restaurantModel.restaurantName + " Price: "+restaurantModel.price + " Rating: "+restaurantModel.rating)
            }
            return newListRestaurant
        }
        //By default, Price
        Collections.sort(newListRestaurant,SortByPrice())

        for (restaurantModel:RestaurantModel in newListRestaurant){
            println("Restaurant Id: "+restaurantModel.restaurantId + " Restaurant Name: "+restaurantModel.restaurantName + " Price: "+restaurantModel.price + " Rating: "+restaurantModel.rating)
        }
        return newListRestaurant
    }

    fun placeOrder(restaurantName: String, quantity: Int): OrderModel {
        val restaurantModel: RestaurantModel = restaurantNameMap[restaurantName]
            ?: return throw CustomException("No Restaurant found with given name")

        if(restaurantModel.quantity == 0){
            return throw CustomException("Restaurant is out of stock! please try again later")
        }
        if(restaurantModel.quantity!! < quantity){
            return throw CustomException("Restaurant is only "+restaurantModel.quantity)
        }
        val orderModel = OrderModel()
        orderModel.orderId = IdGenerator.getId()
        orderModel.quantity = quantity
        orderModel.price = quantity* restaurantModel.price!!
        orderModel.item = restaurantModel.item
        orderModel.userId = loggedInUser?.userId?.toString()

        //update quantity
        restaurantModel.quantity = restaurantModel.quantity!! - quantity

        //add to order user history
        loggedInUser?.listOrder?.add(orderModel)
        return orderModel
    }

    fun listOrderHistory() {
        for(orderModel:OrderModel in loggedInUser?.listOrder!!){
            println("Order ID: "+orderModel.orderId + " item "+orderModel.item + " Quantity: "+orderModel.quantity + " Price: "+orderModel.price)
        }
//        return loggedInUser?.listOrder
    }

    class SortByRating : Comparator<RestaurantModel> {
        override fun compare(o1: RestaurantModel?, o2: RestaurantModel?): Int {
            if(o1?.rating == 0.0.toFloat() || o2?.rating == 0.0.toFloat()){
                return 0
            }else if(o1?.rating!! > o2?.rating!!){
                return 1
            }else if(o1.rating == o2.rating){
                return 0
            }else{
                return -1
            }
        }

    }

    class SortByPrice : Comparator<RestaurantModel> {
        override fun compare(o1: RestaurantModel?, o2: RestaurantModel?): Int {
            return o1?.price?.minus(o2?.price!!)!!
        }

    }

}