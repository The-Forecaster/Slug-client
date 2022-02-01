package trans.rights.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark an object to be added to the registry
 * @see Priority
 * 
 * @author Austin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EventHandler {
    /**
     * Priority of the method 
     * 
     * @return int value of the Priority, default is -50
     */
    int priority() default Priority.DEFALUT;
}
