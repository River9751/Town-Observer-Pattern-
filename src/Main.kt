import java.util.*

interface Customer {  //Observer
    fun getMeal(mealToday: Product)
}

interface ISubject {
    fun registerObserver(customer: Customer)
    fun removeObserver(customer: Customer)
    fun notifyObservers(product: Product)
}

//Observers
class RiverHospital : Customer {
    override fun getMeal(mealToday: Product) {
        println("Hospital received Today’s ${mealToday.name} Special")
    }
}

class RiverFireStation : Customer {
    override fun getMeal(mealToday: Product) {
        println("FireStation received Today’s ${mealToday.name} Special")
    }
}

class RiverSchool : Customer {
    override fun getMeal(mealToday: Product) {
        println("School received Today’s ${mealToday.name} Special")
    }
}

class RiverStudio : Customer {
    override fun getMeal(mealToday: Product) {
        println("Studio received Today’s ${mealToday.name} Special")
    }
}

//Subject
class RiverBakery : ISubject {
    private val list: ArrayList<Customer> = arrayListOf()

    override fun registerObserver(customer: Customer) {
        list.add(customer)
    }

    override fun removeObserver(customer: Customer) {
        list.remove(customer)
    }

    override fun notifyObservers(product: Product) {
        for (c in list) {
            c.getMeal(product)
        }
    }
}

enum class Category {
    NOODLE,
    RICE,
    BREAD
}

data class Product(val name: String, val category: Category)

fun getProducts(): ArrayList<Product> {
    val list = arrayListOf<Product>()
    list.add(Product("A", Category.BREAD))
    list.add(Product("B", Category.RICE))
    list.add(Product("C", Category.BREAD))
    list.add(Product("D", Category.NOODLE))
    list.add(Product("E", Category.NOODLE))
    list.add(Product("F", Category.BREAD))
    list.add(Product("G", Category.BREAD))
    list.add(Product("H", Category.RICE))
    list.add(Product("I", Category.BREAD))
    list.add(Product("J", Category.RICE))
    return list
}

fun main(args: Array<String>) {
    //Fake data
    val products = getProducts()
    val todaySpecial = products.random()

    //Concrete Subject
    val bakery = RiverBakery()

    //Concrete Observer
    val hospital = RiverHospital()
    val fireStation = RiverFireStation()
    val school = RiverSchool()
    val studio = RiverStudio()

    bakery.registerObserver(hospital)
    bakery.registerObserver(fireStation)
    bakery.registerObserver(school)
    bakery.registerObserver(studio)

    //Try remove
    bakery.removeObserver(school)

    //Notify
    bakery.notifyObservers(todaySpecial)
}