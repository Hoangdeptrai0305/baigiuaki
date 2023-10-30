package com.example.myapplication
import java.util.*
data class quanlymodel(
    var id: Int = getAutoId(),
    var name: String = "",
    var email:String = "",
    var password:String="",
    var phone :String =""
) {
    companion object{
        fun getAutoId(): Int {
            var radom = Random()
            return radom.nextInt(100)
        }
    }
}