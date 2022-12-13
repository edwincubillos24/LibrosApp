package com.edwinacubillos.librosapp.firebase

sealed class ResourceRemote<T>(
    var data: T? = null,
    var message: String? = null,
    var status: Status? = null,
    var errorCode: Int? = null
) {
    class Success<T>(data: T) : ResourceRemote<T>(data = data, status = Status.Success)

    class Error<T>(errorCode: Int? = null, message: String? = null) :
            ResourceRemote<T>(errorCode = errorCode, message = message, status = Status.Error)

    class Loading<T>(message: String? = null) : ResourceRemote<T>(message = message, status = Status.Loading)
}

enum class Status {
    Success {
        override fun toString(): String {
            return this.name
        }
    },
    Error {
        override fun toString(): String {
            return this.name
        }
    },
    Loading {
        override fun toString(): String {
            return this.name
        }
    }
}
