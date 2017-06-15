package instantmessage.client.helper;

import java.util.EnumSet;

/**
 * This helper is used to manage enums Reference:
 * http://techqa.info/programming/question/2199399/storing-enumset-in-a-database
 * 
 * @author Tao Liu
 *
 */
public class EnumHelper {

	/**
	 * This method is used to combine enums and return the enums as a long
	 * 
	 * @param arg0
	 * @return
	 */
	public static <E extends Enum<E>> long encode(E... arg0) {

		EnumSet<E> set = EnumSet.of(arg0[0], arg0);

		long ret = 0;

		for (E val : set) {
			ret |= 1L << val.ordinal();
		}

		return ret;
	}
}
