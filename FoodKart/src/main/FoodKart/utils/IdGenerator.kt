package utils

class IdGenerator {
    companion object{
        private var id:Int = 0

        public fun getId():Int{
            id++;
            return id
        }
    }
}