import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

fun main(args: Array<String>) {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://64f9e5704098a7f2fc152351.mockapi.io/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()
    val apiUser = retrofit.create(ApiUsers::class.java)
    val userData = apiUser.getUsers()

    userData.enqueue(object : Callback<List<Users>> {
        override fun onResponse(userData: Call<List<Users>>, reponse: Response<List<Users>>) {
            if (reponse.isSuccessful) {
                val users = reponse.body()
                if (users != null) {
                    for (user in users) {
                        println("User : ${user._id} | name: ${user.name} | email: ${user.email} | password: ${user.password}")
                    }
                }
            } else {
                println("Erreur de réponse : ${reponse.code()}")
            }
        }
        override fun onFailure(userData: Call<List<Users>>, t: Throwable) {
            println("Erreur de réseau : ${t.message}")
        }
    })
}