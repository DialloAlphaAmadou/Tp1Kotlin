import retrofit2.Call
import retrofit2.http.GET

interface ApiUsers {

    @GET("users")
    fun getUsers(): Call<List<Users>>

}