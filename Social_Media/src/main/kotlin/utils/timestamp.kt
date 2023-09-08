package utils

import java.sql.Timestamp

class timestamp {
    companion object {
        fun setTimeStamp(currentMillis:Long): String {

            val timeStamp = Timestamp(currentMillis)
            return timeStamp.toString()
        }
    }
}