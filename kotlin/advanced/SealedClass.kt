sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
  }

fun fetchData(shouldSucceed: Boolean): Result<String> {
    return if (shouldSucceed) {
        Result.Success("Data fetched successfully!")
    } else {
        Result.Error(Exception("Failed to fetch data."))
    }
}

fun processResult(result: Result<String>) {
    when (result) {
        is Result.Success -> println("Success: ${result.data}")
        is Result.Error -> println("Error: ${result.exception.message}")
        is Result.Loading -> println("Loading...")
    }
}

fun main() {
    val successResult = fetchData(true)
    processResult(successResult)

    val errorResult = fetchData(false)
    processResult(errorResult)

    processResult(Result.Loading)
}
