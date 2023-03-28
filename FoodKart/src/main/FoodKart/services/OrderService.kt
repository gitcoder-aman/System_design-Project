package services

import data.Dao
import exception.CustomException
import model.OrderModel

class OrderService{
    private fun orderService(){

    }
    companion object{
        @Volatile private var instance: OrderService? = null

        fun getInstance(): OrderService {
            if(instance == null){
                synchronized(this){
                    if(instance == null){
                        instance = OrderService()
                    }
                }
            }
            return instance!!
        }
    }
    val orderDao:Dao = Dao.getInstance()

    public fun placeOrder(restaurantName:String,quantity:Int):OrderModel{
        if(quantity <= 0){
            return throw CustomException("Quantity is Invalid")
        }
        return orderDao.placeOrder(restaurantName,quantity)
    }
    public fun listOrderHistory() {
         orderDao.listOrderHistory()
    }

}