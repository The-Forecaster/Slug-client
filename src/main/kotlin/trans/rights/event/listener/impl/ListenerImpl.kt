package trans.rights.event.listener.impl

import java.lang.reflect.Method
import trans.rights.event.listener.Listener

class MethodListener<T: Any>(
        private val action: Method,
        override val priority: Int,
        override val parent: Any,
        override val target: Class<T>
) : Listener<T> {

    override operator fun invoke(param: T) {
        this.action.invoke(this.parent, param)
    }
}

class LambdaListener<T: Any?>(
    private val action: (T) -> Unit,
    override val priority: Int,
    override val parent: Any,
    override val target: Class<T>
) : Listener<T> {

    override operator fun invoke(param: T) {
        this.action.invoke(param)
    }
}
