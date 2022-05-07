package trans.rights.event.type

abstract class Cancellable {
    var isCancelled = false

    fun cancel() {
        this.isCancelled = true
    }
}