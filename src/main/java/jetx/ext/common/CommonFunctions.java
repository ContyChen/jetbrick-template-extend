package jetx.ext.common;

import java.util.Random;
import java.util.UUID;

import jetbrick.template.JetAnnotations.Functions;

/**
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.1
 */
@Functions
public class CommonFunctions {

	/*
	 * UUID
	 */
	public static String randomUuid() {
		return randomUuid(true);
	}

	public static String randomUuid(boolean lowerCase) {
		return randomUuid(lowerCase, "-");
	}

	public static String randomUuid(boolean lowerCase, String separator) {
		String str = UUID.randomUUID().toString();
		if (!separator.equals("-")) {
			str = str.replaceAll("-", separator);
		}
		str = lowerCase ? str.toLowerCase() : str.toUpperCase();
		return str;
	}

	/*
	 * random string
	 */
	private static final Random RANDOM = new Random();

	public static String random(int count) {
		return random(count, false, false);
	}

	public static String randomAscii(int count) {
		return random(count, 32, 127, false, false);
	}

	public static String randomAlphabetic(int count) {
		return random(count, true, false);
	}

	public static String randomAlphanumeric(int count) {
		return random(count, true, true);
	}

	public static String randomNumeric(int count) {
		return random(count, false, true);
	}

	public static String random(int count, boolean letters, boolean numbers) {
		return random(count, 0, 0, letters, numbers);
	}

	public static String random(int count, int start, int end, boolean letters,
			boolean numbers) {
		return random(count, start, end, letters, numbers, null, RANDOM);
	}

	public static String random(int count, int start, int end, boolean letters,
			boolean numbers, char... chars) {
		return random(count, start, end, letters, numbers, chars, RANDOM);
	}

	public static String random(int count, int start, int end, boolean letters,
			boolean numbers, char[] chars, Random random) {
		if (count == 0) {
			return "";
		} else if (count < 0) {
			throw new IllegalArgumentException(
					"Requested random string length " + count
							+ " is less than 0.");
		}
		if (start == 0 && end == 0) {
			end = 'z' + 1;
			start = ' ';
			if (!letters && !numbers) {
				start = 0;
				end = Integer.MAX_VALUE;
			}
		}

		char[] buffer = new char[count];
		int gap = end - start;

		while (count-- != 0) {
			char ch;
			if (chars == null) {
				ch = (char) (random.nextInt(gap) + start);
			} else {
				ch = chars[random.nextInt(gap) + start];
			}
			if (letters && Character.isLetter(ch) || numbers
					&& Character.isDigit(ch) || !letters && !numbers) {
				if (ch >= 56320 && ch <= 57343) {
					if (count == 0) {
						count++;
					} else {
						// low surrogate, insert high surrogate after putting it
						// in
						buffer[count] = ch;
						count--;
						buffer[count] = (char) (55296 + random.nextInt(128));
					}
				} else if (ch >= 55296 && ch <= 56191) {
					if (count == 0) {
						count++;
					} else {
						// high surrogate, insert low surrogate before putting
						// it in
						buffer[count] = (char) (56320 + random.nextInt(128));
						count--;
						buffer[count] = ch;
					}
				} else if (ch >= 56192 && ch <= 56319) {
					// private high surrogate, no effing clue, so skip it
					count++;
				} else {
					buffer[count] = ch;
				}
			} else {
				count++;
			}
		}
		return new String(buffer);
	}

	public static String random(int count, String chars) {
		if (chars == null) {
			return random(count, 0, 0, false, false, null, RANDOM);
		}
		return random(count, chars.toCharArray());
	}

	public static String random(int count, char... chars) {
		if (chars == null) {
			return random(count, 0, 0, false, false, null, RANDOM);
		}
		return random(count, 0, chars.length, false, false, chars, RANDOM);
	}

}
