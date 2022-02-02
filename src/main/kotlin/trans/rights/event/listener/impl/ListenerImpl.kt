package trans.rights.event.listener.impl

import trans.rights.event.listener.Listener
import java.lang.reflect.Method

class MethodListener<T>(priority: Int,  parent: Any?, action: Method, override val target: Class<T>) : Listener<T> {
    override val priority: Int

    override val parent: Any?

    private val action: Method

    init {
        this.priority = priority
        this.parent = parent
        this.action = action
    }

    override operator fun invoke(param: T?) {
        this.action.invoke(this.parent, param)
    }
}

class LambdaListener<T>(priority: Int, parent: Any?, action: (T?) -> Unit, override val target: Class<T>) : Listener<T> {
    override val priority: Int

    override val parent: Any?

    private val action: (T?) -> Unit

    init {
        this.priority = priority
        this.parent = parent
        this.action = action
    }

    override operator fun invoke(param: T?) {
        this.action.invoke(param)
    }
}
