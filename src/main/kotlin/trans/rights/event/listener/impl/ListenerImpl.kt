package trans.rights.event.listener.impl

import trans.rights.event.listener.Listener
import java.lang.reflect.Method

class MethodListener<T>(action: Method, override val priority: Int, override val parent: Any?, override val target: Class<T>) : Listener<T> {
    val action: Method

    init {
        this.action = action
    }

    override operator fun invoke(param: T) {
        this.action.invoke(this.parent, param)
    }
}

class LambdaListener<T>(action: (T) -> Unit, override val priority: Int, override val parent: Any?, override val target: Class<T>) : Listener<T> {
    private val action: (T) -> Unit

    init {
        this.action = action
    }

    override operator fun invoke(param: T) {
        this.action.invoke(param)
    }
}
