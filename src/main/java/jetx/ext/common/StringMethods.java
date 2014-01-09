package jetx.ext.common;

import jetbrick.template.JetAnnotations.Methods;

/**
 * 对java.lang.String做的method
 * 
 * @author 应卓(yingzhor@gmail.com)
 */
@Methods
public final class StringMethods {

    public static final String EMPTY = "";

    public static final int INDEX_NOT_FOUND = -1;
    
    private static final int PAD_LIMIT = 8192;
    
    /**
     * <p>Capitalizes a String changing the first letter to title case as
     * per {@link Character#toTitleCase(char)}. No other letters are changed.</p>
     *
     * <p>For a word based algorithm, see {@link org.apache.commons.lang3.text.WordUtils#capitalize(String)}.
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringMethods.capitalize(null)  = null
     * StringMethods.capitalize("")    = ""
     * StringMethods.capitalize("cat") = "Cat"
     * StringMethods.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return the capitalized String, {@code null} if null String input
     * @see #uncapitalize(String)
     */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen)
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}
	
    /**
     * <p>Uncapitalizes a String changing the first letter to title case as
     * per {@link Character#toLowerCase(char)}. No other letters are changed.</p>
     *
     * <p>For a word based algorithm, see {@link org.apache.commons.lang3.text.WordUtils#uncapitalize(String)}.
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringMethods.uncapitalize(null)  = null
     * StringMethods.uncapitalize("")    = ""
     * StringMethods.uncapitalize("Cat") = "cat"
     * StringMethods.uncapitalize("CAT") = "cAT"
     * </pre>
     *
     * @param str the String to uncapitalize, may be null
     * @return the uncapitalized String, {@code null} if null String input
     * @see #capitalize(String)
     */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen)
				.append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}
	
    /**
     * <p>Swaps the case of a String changing upper and title case to
     * lower case, and lower case to upper case.</p>
     *
     * <ul>
     *  <li>Upper case character converts to Lower case</li>
     *  <li>Title case character converts to Lower case</li>
     *  <li>Lower case character converts to Upper case</li>
     * </ul>
     *
     * <p>For a word based algorithm, see {@link org.apache.commons.lang3.text.WordUtils#swapCase(String)}.
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringMethods.swapCase(null)                 = null
     * StringMethods.swapCase("")                   = ""
     * StringMethods.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer performs a word based algorithm.
     * If you only use ASCII, you will notice no change.
     * That functionality is available in org.apache.commons.lang3.text.WordUtils.</p>
     *
     * @param str  the String to swap case, may be null
     * @return the changed String, {@code null} if null String input
     */
    public static String swapCase(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char[] buffer = str.toCharArray();

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                buffer[i] = Character.toUpperCase(ch);
            }
        }
        return new String(buffer);
    }
    
    /**
     * <p>Checks if a CharSequence is empty ("") or null.</p>
     *
     * <pre>
     * StringMethods.isEmpty(null)      = true
     * StringMethods.isEmpty("")        = true
     * StringMethods.isEmpty(" ")       = false
     * StringMethods.isEmpty("bob")     = false
     * StringMethods.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is empty or null
     */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isEmpty2(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	
    /**
     * <p>Checks if a CharSequence is not empty ("") and not null.</p>
     *
     * <pre>
     * StringMethods.isNotEmpty(null)      = false
     * StringMethods.isNotEmpty("")        = false
     * StringMethods.isNotEmpty(" ")       = true
     * StringMethods.isNotEmpty("bob")     = true
     * StringMethods.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null
     * @since 3.0 Changed signature from isNotEmpty(String) to isNotEmpty(CharSequence)
     */
	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}
	
    /**
     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringMethods.isBlank(null)      = true
     * StringMethods.isBlank("")        = true
     * StringMethods.isBlank(" ")       = true
     * StringMethods.isBlank("bob")     = false
     * StringMethods.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringMethods.isNotBlank(null)      = false
     * StringMethods.isNotBlank("")        = false
     * StringMethods.isNotBlank(" ")       = false
     * StringMethods.isNotBlank("bob")     = true
     * StringMethods.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     *  not empty and not null and not whitespace
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
    
    /**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String, handling {@code null} by returning
     * {@code null}.</p>
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     * To strip whitespace use {@link #strip(String)}.</p>
     *
     * <p>To trim your choice of characters, use the
     * {@link #strip(String, String)} methods.</p>
     *
     * <pre>
     * StringMethods.trim(null)          = null
     * StringMethods.trim("")            = ""
     * StringMethods.trim("     ")       = ""
     * StringMethods.trim("abc")         = "abc"
     * StringMethods.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str  the String to be trimmed, may be null
     * @return the trimmed string, {@code null} if null String input
     */
    public static String trim(String str) {
    	return str == null ? null : str.trim();
    }
    
	public static String trim2(String str) {
		return str == null ? null : str.trim();
	}
	
    /**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String returning {@code null} if the String is
     * empty ("") after the trim or if it is {@code null}.
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     * To strip whitespace use {@link #stripToNull(String)}.</p>
     *
     * <pre>
     * StringMethods.trimToNull(null)          = null
     * StringMethods.trimToNull("")            = null
     * StringMethods.trimToNull("     ")       = null
     * StringMethods.trimToNull("abc")         = "abc"
     * StringMethods.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str  the String to be trimmed, may be null
     * @return the trimmed String,
     *  {@code null} if only chars &lt;= 32, empty or null String input
     */
	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}
	
    /**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String returning an empty String ("") if the String
     * is empty ("") after the trim or if it is {@code null}.
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     * To strip whitespace use {@link #stripToEmpty(String)}.</p>
     *
     * <pre>
     * StringMethods.trimToEmpty(null)          = ""
     * StringMethods.trimToEmpty("")            = ""
     * StringMethods.trimToEmpty("     ")       = ""
     * StringMethods.trimToEmpty("abc")         = "abc"
     * StringMethods.trimToEmpty("    abc    ") = "abc"
     * </pre>
     *
     * @param str  the String to be trimmed, may be null
     * @return the trimmed String, or an empty String if {@code null} input
     */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}
	
    /**
     * <p>Gets the leftmost {@code len} characters of a String.</p>
     *
     * <p>If {@code len} characters are not available, or the
     * String is {@code null}, the String will be returned without
     * an exception. An empty String is returned if len is negative.</p>
     *
     * <pre>
     * StringMethods.left(null, *)    = null
     * StringMethods.left(*, -ve)     = ""
     * StringMethods.left("", *)      = ""
     * StringMethods.left("abc", 0)   = ""
     * StringMethods.left("abc", 2)   = "ab"
     * StringMethods.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str  the String to get the leftmost characters from, may be null
     * @param len  the length of the required String
     * @return the leftmost characters, {@code null} if null String input
     */
	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}
	
    /**
     * <p>Gets the rightmost {@code len} characters of a String.</p>
     *
     * <p>If {@code len} characters are not available, or the String
     * is {@code null}, the String will be returned without an
     * an exception. An empty String is returned if len is negative.</p>
     *
     * <pre>
     * StringMethods.right(null, *)    = null
     * StringMethods.right(*, -ve)     = ""
     * StringMethods.right("", *)      = ""
     * StringMethods.right("abc", 0)   = ""
     * StringMethods.right("abc", 2)   = "bc"
     * StringMethods.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str  the String to get the rightmost characters from, may be null
     * @param len  the length of the required String
     * @return the rightmost characters, {@code null} if null String input
     */
	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}
	
    /**
     * <p>Gets {@code len} characters from the middle of a String.</p>
     *
     * <p>If {@code len} characters are not available, the remainder
     * of the String will be returned without an exception. If the
     * String is {@code null}, {@code null} will be returned.
     * An empty String is returned if len is negative or exceeds the
     * length of {@code str}.</p>
     *
     * <pre>
     * StringMethods.mid(null, *, *)    = null
     * StringMethods.mid(*, *, -ve)     = ""
     * StringMethods.mid("", 0, *)      = ""
     * StringMethods.mid("abc", 0, 2)   = "ab"
     * StringMethods.mid("abc", 0, 4)   = "abc"
     * StringMethods.mid("abc", 2, 4)   = "c"
     * StringMethods.mid("abc", 4, 2)   = ""
     * StringMethods.mid("abc", -2, 2)  = "ab"
     * </pre>
     *
     * @param str  the String to get the characters from, may be null
     * @param pos  the position to start from, negative treated as zero
     * @param len  the length of the required String
     * @return the middle characters, {@code null} if null String input
     */
	public static String mid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0 || pos > str.length()) {
			return EMPTY;
		}
		if (pos < 0) {
			pos = 0;
		}
		if (str.length() <= pos + len) {
			return str.substring(pos);
		}
		return str.substring(pos, pos + len);
	}
	
    /**
     * <p>Repeat a String {@code repeat} times to form a
     * new String.</p>
     *
     * <pre>
     * StringMethods.repeat(null, 2) = null
     * StringMethods.repeat("", 0)   = ""
     * StringMethods.repeat("", 2)   = ""
     * StringMethods.repeat("a", 3)  = "aaa"
     * StringMethods.repeat("ab", 2) = "abab"
     * StringMethods.repeat("a", -2) = ""
     * </pre>
     *
     * @param str  the String to repeat, may be null
     * @param repeat  number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     *  {@code null} if null String input
     */
    public static String repeat(String str, int repeat) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1 :
                return repeat(str.charAt(0), repeat);
            case 2 :
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default :
                StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }
    
    /**
     * <p>Repeat a String {@code repeat} times to form a
     * new String, with a String separator injected each time. </p>
     *
     * <pre>
     * StringMethods.repeat(null, null, 2) = null
     * StringMethods.repeat(null, "x", 2)  = null
     * StringMethods.repeat("", null, 0)   = ""
     * StringMethods.repeat("", "", 2)     = ""
     * StringMethods.repeat("", "x", 3)    = "xxx"
     * StringMethods.repeat("?", ", ", 3)  = "?, ?, ?"
     * </pre>
     *
     * @param str        the String to repeat, may be null
     * @param separator  the String to inject, may be null
     * @param repeat     number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     *  {@code null} if null String input
     */
    public static String repeat(String str, String separator, int repeat) {
        if(str == null || separator == null) {
            return repeat(str, repeat);
        } else {
            // given that repeat(String, int) is quite optimized, better to rely on it than try and splice this into it
            String result = repeat(str + separator, repeat);
            return removeEnd(result, separator);
        }
    }
    
	private static String repeat(char ch, int repeat) {
		char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
			buf[i] = ch;
		}
		return new String(buf);
	}

	private static String removeEnd(String str, String remove) {
		if (isEmpty(str) || isEmpty(remove)) {
			return str;
		}
		if (str.endsWith(remove)) {
			return str.substring(0, str.length() - remove.length());
		}
		return str;
	}
    
    /**
     * <p>Reverses a String as per {@link StringBuilder#reverse()}.</p>
     *
     * <p>A {@code null} String returns {@code null}.</p>
     *
     * <pre>
     * StringMethods.reverse(null)  = null
     * StringMethods.reverse("")    = ""
     * StringMethods.reverse("bat") = "tab"
     * </pre>
     *
     * @param str  the String to reverse, may be null
     * @return the reversed String, {@code null} if null String input
     */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}
	
    /**
     * <p>Checks if the CharSequence contains only lowercase characters.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringMethods.isAllLowerCase(null)   = false
     * StringMethods.isAllLowerCase("")     = false
     * StringMethods.isAllLowerCase("  ")   = false
     * StringMethods.isAllLowerCase("abc")  = true
     * StringMethods.isAllLowerCase("abC") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains lowercase characters, and is non-null
     */
	public static boolean isAllLowerCase(CharSequence cs) {
		if (cs == null || isEmpty(cs)) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLowerCase(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only uppercase characters.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty String (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringMethods.isAllUpperCase(null)   = false
     * StringMethods.isAllUpperCase("")     = false
     * StringMethods.isAllUpperCase("  ")   = false
     * StringMethods.isAllUpperCase("ABC")  = true
     * StringMethods.isAllUpperCase("aBC") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains uppercase characters, and is non-null
     */
	public static boolean isAllUpperCase(CharSequence cs) {
		if (cs == null || isEmpty(cs)) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isUpperCase(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
    /**
     * <p>Checks if the CharSequence contains only Unicode letters.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringMethods.isAlpha(null)   = false
     * StringMethods.isAlpha("")     = false
     * StringMethods.isAlpha("  ")   = false
     * StringMethods.isAlpha("abc")  = true
     * StringMethods.isAlpha("ab2c") = false
     * StringMethods.isAlpha("ab-c") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains letters, and is non-null
     */
	public static boolean isAlpha(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only Unicode letters and
     * space (' ').</p>
     *
     * <p>{@code null} will return {@code false}
     * An empty CharSequence (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringMethods.isAlphaSpace(null)   = false
     * StringMethods.isAlphaSpace("")     = true
     * StringMethods.isAlphaSpace("  ")   = true
     * StringMethods.isAlphaSpace("abc")  = true
     * StringMethods.isAlphaSpace("ab c") = true
     * StringMethods.isAlphaSpace("ab2c") = false
     * StringMethods.isAlphaSpace("ab-c") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains letters and space,
     *  and is non-null
     */
	public static boolean isAlphaSpace(CharSequence cs) {
		if (cs == null) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(cs.charAt(i)) == false
					&& cs.charAt(i) != ' ') {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only Unicode letters or digits.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringMethods.isAlphanumeric(null)   = false
     * StringMethods.isAlphanumeric("")     = false
     * StringMethods.isAlphanumeric("  ")   = false
     * StringMethods.isAlphanumeric("abc")  = true
     * StringMethods.isAlphanumeric("ab c") = false
     * StringMethods.isAlphanumeric("ab2c") = true
     * StringMethods.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains letters or digits,
     *  and is non-null
     */
	public static boolean isAlphanumeric(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetterOrDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only Unicode letters, digits
     * or space ({@code ' '}).</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringMethods.isAlphanumericSpace(null)   = false
     * StringMethods.isAlphanumericSpace("")     = true
     * StringMethods.isAlphanumericSpace("  ")   = true
     * StringMethods.isAlphanumericSpace("abc")  = true
     * StringMethods.isAlphanumericSpace("ab c") = true
     * StringMethods.isAlphanumericSpace("ab2c") = true
     * StringMethods.isAlphanumericSpace("ab-c") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains letters, digits or space,
     *  and is non-null
     */
	public static boolean isAlphanumericSpace(CharSequence cs) {
		if (cs == null) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetterOrDigit(cs.charAt(i)) == false
					&& cs.charAt(i) != ' ') {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only ASCII printable characters.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringMethods.isAsciiPrintable(null)     = false
     * StringMethods.isAsciiPrintable("")       = true
     * StringMethods.isAsciiPrintable(" ")      = true
     * StringMethods.isAsciiPrintable("Ceki")   = true
     * StringMethods.isAsciiPrintable("ab2c")   = true
     * StringMethods.isAsciiPrintable("!ab-c~") = true
     * StringMethods.isAsciiPrintable("\u0020") = true
     * StringMethods.isAsciiPrintable("\u0021") = true
     * StringMethods.isAsciiPrintable("\u007e") = true
     * StringMethods.isAsciiPrintable("\u007f") = false
     * StringMethods.isAsciiPrintable("Ceki G\u00fclc\u00fc") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if every character is in the range
     *  32 thru 126
     */
	public static boolean isAsciiPrintable(CharSequence cs) {
		if (cs == null) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if ((cs.charAt(i) >= 32 && cs.charAt(i) < 127) == false) {
				return false;

			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only Unicode digits.
     * A decimal point is not a Unicode digit and returns false.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringMethods.isNumeric(null)   = false
     * StringMethods.isNumeric("")     = false
     * StringMethods.isNumeric("  ")   = false
     * StringMethods.isNumeric("123")  = true
     * StringMethods.isNumeric("12 3") = false
     * StringMethods.isNumeric("ab2c") = false
     * StringMethods.isNumeric("12-3") = false
     * StringMethods.isNumeric("12.3") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains digits, and is non-null
     */
	public static boolean isNumeric(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only Unicode digits or space
     * ({@code ' '}).
     * A decimal point is not a Unicode digit and returns false.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringMethods.isNumericSpace(null)   = false
     * StringMethods.isNumericSpace("")     = true
     * StringMethods.isNumericSpace("  ")   = true
     * StringMethods.isNumericSpace("123")  = true
     * StringMethods.isNumericSpace("12 3") = true
     * StringMethods.isNumericSpace("ab2c") = false
     * StringMethods.isNumericSpace("12-3") = false
     * StringMethods.isNumericSpace("12.3") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains digits or space,
     *  and is non-null
     */
	public static boolean isNumericSpace(CharSequence cs) {
		if (cs == null) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false && cs.charAt(i) != ' ') {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>Checks if the CharSequence contains only whitespace.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code true}.</p>
     *
     * <pre>
     * StringMethods.isWhitespace(null)   = false
     * StringMethods.isWhitespace("")     = true
     * StringMethods.isWhitespace("  ")   = true
     * StringMethods.isWhitespace("abc")  = false
     * StringMethods.isWhitespace("ab2c") = false
     * StringMethods.isWhitespace("ab-c") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains whitespace, and is non-null
     */
	public static boolean isWhitespace(CharSequence cs) {
		if (cs == null) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
    /**
     * <p>Gets the leftmost {@code len} characters of a String.
     * and pad with " ...".
     * </p>
     *
     * <p>If {@code len} characters are not available, or the
     * String is {@code null}, the String will be returned without
     * an exception. An empty String is returned if len is negative.</p>
     *
     * <pre>
     * StringMethods.alignAt(null, *)    			= null
     * StringMethods.alignAt("abcdefg", 3)			= "abcdef ..."
     * </pre>
     *
     * @param str  the String to get the leftmost characters from, may be null
     * @param len  the length of the required String
     * @return the leftmost characters and pad with " ...", {@code null} if null String input
     * 
     * @author janrn
     * @since 1.0.4
     */
	public static String alignAt(String text, int length) {
		return alignAt(text, length, " ...");
	}
	
    /**
     * <p>Gets the leftmost {@code len} characters of a String.
     * and pad with another string. 
     * </p>
     *
     * <p>If {@code len} characters are not available, or the
     * String is {@code null}, the String will be returned without
     * an exception. An empty String is returned if len is negative.</p>
     *
     * <pre>
     * StringMethods.alignAt(null, *, *)    			= null
     * StringMethods.alignAt("abcdefg", 3, "")			= "abcdef"
     * StringMethods.alignAt("abcdefg", 3, "...")		= "abcdef..."
     * </pre>
     *
     * @param str  the String to get the leftmost characters from, may be null
     * @param len  the length of the required String
     * @param padWith another string append to the leftmost characters
     * @return the leftmost characters and pad with another string, {@code null} if null String input
     * 
     * @author janrn
     * @since 1.0.4
     */
	public static String alignAt(String str, int length, String padWith) {
		if (str == null) {
			return str;
		}
		
		int textLength = str.length();
		int byteLength = 0;
		StringBuffer returnStr = new StringBuffer();
		int i = 0;
		for (; i < textLength && byteLength < length * 2; i++) {
			char charStr = str.charAt(i);
			if (charStr >= 0x4e00 && charStr <= 0x9fa5)
				byteLength += 2;
			else
				byteLength++;
			returnStr.append(charStr);
		}
		if (i < textLength && padWith != null) {
			returnStr.append(padWith);
		}
		return returnStr.toString();
	}
}
