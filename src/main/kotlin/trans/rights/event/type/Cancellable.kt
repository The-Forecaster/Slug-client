package trans.rights.event.type

abstract class Cancellable {
    var isCancelled = false
        private set

    fun cancel() {
        this.isCancelled = true
    }
}