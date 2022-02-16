package trans.rights.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark something to be added to the subscriber registry
 * 
 * If you annotate a method you will need to specify the priority in this
 * annotation if you want to have a custom priority
 * 
 * If you are annotating an object then define the priority in the object,
 * changing it here will do nothing
 * 
 * @see Priority
 * 
 * @author Austin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface EventHandler {
    /**
     * Priority of the method
     * 
     * @return int value of the Priority, default is -50
     */
    int priority() default Priority.DEFAULT;
}
