package seven.anno;

import seven.callBack.ConvertInterface;
import seven.callBack.imp.DefaultConvert;

import javax.swing.text.DateFormatter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/#\
//		 .' @          /###\
//		 :         ,   #####
//		  `-..__.-' _.-\###/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

/**
 * @author Seven
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnno {

    String Value() default "Null";

    boolean Pass() default false;

    String Required() default "Null";

    short Align() default 0x2;

    @Deprecated
    Class<? extends ConvertInterface> Convert() default DefaultConvert.class;

    String DateTimeFormatter() default "yyyy-MM-dd hh:mm:ss";
}