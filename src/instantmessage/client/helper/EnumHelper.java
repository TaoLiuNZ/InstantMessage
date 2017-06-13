package instantmessage.client.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;

import instantmessage.client.constant.MessageType;

// Reference: http://techqa.info/programming/question/2199399/storing-enumset-in-a-database
public class EnumHelper {
	public static <E extends Enum<E>> long encode(E...arg0) {
		
		EnumSet<E> set=EnumSet.of(arg0[0], arg0);
		
		
	    long ret = 0;

	    for (E val : set) {
	        ret |= 1L << val.ordinal();
	    }

	    return ret;
	}

	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> EnumSet<E> decode(long code,
	                                                     Class<E> enumType) {
	    try {
	        E[] values = (E[]) enumType.getMethod("values").invoke(null);
	        EnumSet<E> result = EnumSet.noneOf(enumType);
	        while (code != 0) {
	            int ordinal = Long.numberOfTrailingZeros(code);
	            code ^= Long.lowestOneBit(code);
	            result.add(values[ordinal]);
	        }
	        return result;
	    } catch (IllegalAccessException ex) {
	        // Shouldn't happen
	        throw new RuntimeException(ex);
	    } catch (InvocationTargetException ex) {
	        // Probably a NullPointerException, caused by calling this method
	        // from within E's initializer.
	        throw (RuntimeException) ex.getCause();
	    } catch (NoSuchMethodException ex) {
	        // Shouldn't happen
	        throw new RuntimeException(ex);
	    }
	}
}
