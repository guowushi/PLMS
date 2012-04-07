package PLMS.GUI;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TableColumnDefine {
     int order();
}
