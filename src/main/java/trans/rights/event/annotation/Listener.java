package trans.rights.event.annotation;

import java.lang.annotation.*;

/**
 * Used to mark a field to be added to the registry
 * @see Priority
 * 
 * @author Austin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Listener {
    /**
     * Priority of the method 
     * 
     * @return int value of the Priority, default is -50
     */
    int priority() default Priority.DEFAULT;
}
