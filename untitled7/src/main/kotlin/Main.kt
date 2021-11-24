enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

data class Robot(var x: Int, var y: Int, var direction: Direction) {
    fun stepForward() {
        when(direction) {
            Direction.UP -> y += 1
            Direction.DOWN -> y -= 1
            Direction.RIGHT -> x += 1
            Direction.LEFT -> x -= 1

        }
    }

   fun turnLeft() {
       when(direction) {
           Direction.UP -> direction = Direction.LEFT
           Direction.LEFT -> direction = Direction.DOWN
           Direction.DOWN -> direction = Direction.RIGHT
           Direction.RIGHT -> direction = Direction.UP
       }
   }
    fun turnRight() {
        when(direction) {
            Direction.UP -> direction = Direction.RIGHT
            Direction.LEFT -> direction = Direction.UP
            Direction.DOWN -> direction = Direction.LEFT
            Direction.RIGHT -> direction = Direction.DOWN
        }
    }

    fun moveTo(destinationX: Int, destinationY: Int,) {
        if(x < destinationX) {

            while(direction !== Direction.RIGHT) {
                turnLeft()
                print(toString())
            }
            while(x !== destinationX) {
                stepForward()
                print(toString())
            }
        } else if(x > destinationX) {
            while(direction !== Direction.LEFT) {
                turnLeft()
                print(toString())
            }
            while(x !== destinationX) {
                stepForward()
                print(toString())
            }
        }
        if(y < destinationY) {
            while(direction !== Direction.UP) {
                turnLeft()
                print(toString())
            }
            while(y !== destinationY) {
                stepForward()
                print(toString())
            }
        } else if(y > destinationY) {
            while(direction !== Direction.DOWN) {
                turnLeft()
                print(toString())
            }
            while(y !== destinationY) {
                stepForward()
                print(toString())
            }
        }
    }

    override fun toString(): String {
        return "x: $x, y: $y, direction: $direction \n"
    }
}
fun main(args: Array<String>) {
    val r = Robot(1,1, Direction.DOWN)
    r.moveTo(-3, -1)
    print(r.toString())
}