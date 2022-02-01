package trans.rights.event.listener.impl

import trans.rights.event.listener.Listener
import java.lang.reflect.Method

class MethodListener<T : Any?>( parent: Any?, action: Method, priority: Int, override val target: Class<T>) : Listener<T> {
    override val priority: Int

    override val parent: Any?

    private val action: Method

    init {
        this.priority = priority
        this.parent = parent
        this.action = action
    }

    override fun invoke(param: T?) {
        this.action.invoke(this.parent, param)
    }
}