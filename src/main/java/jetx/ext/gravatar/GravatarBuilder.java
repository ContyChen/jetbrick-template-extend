package jetx.ext.gravatar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.2
 */
public class GravatarBuilder {
	
    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

	private final static int DEFAULT_SIZE = 80;
	private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
	private final static String FILE_TYPE_EXTENSION = ".jpg";

    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
	
	private int          size  = DEFAULT_SIZE;
	private String       email = null;
	private Rating       rating = Rating.G;
	private DefaultImage defaultImage = DefaultImage.IDENTICON;
	
    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
	
	public static GravatarBuilder create() {
		return new GravatarBuilder();
	}
	
	/**
	 * Create a builder
	 */
	public GravatarBuilder() {
		super();
	}
	
	/**
	 * Create a builder and init email
	 */
	public GravatarBuilder(String email) {
		this();
		email(email);
	}

    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/
	
	/**
	 * Set email.
	 */
	public GravatarBuilder email(String email) {
		_validate(email);
		this.email = email.trim();
		return this;
	}

	/**
	 * Set size of image. 
	 */
	public GravatarBuilder size(int sizeInPixels) {
		_validate(size);
		this.size = sizeInPixels;
		return this;
	}
	
	/**
	 * Set rating 
	 */
	public GravatarBuilder rating(Rating rating) {
		this.rating = rating;
		return this;
	}
	
	/**
	 * Set default image type. 
	 */
	public GravatarBuilder defaultImage(DefaultImage defaultImage) {
		this.defaultImage = defaultImage;
		return this;
	}
	
	/**
	 * build the url string
	 */
	public String build() {
		_validate(email);
		
		StringBuilder builder = new StringBuilder(GRAVATAR_URL + _md5(email.toLowerCase()) + FILE_TYPE_EXTENSION);
		builder.append("?s=" + size);
		builder.append("&r=" + rating.getKey());
		builder.append("&d=" + defaultImage.getKey());
		
		String urlString =  builder.toString();
		return urlString;
	}
	
    /*--------------------------------------------
    |            H E L P - M E T H O D S        |
    ============================================*/

	private void _validate(String email) {
		if (email == null) {
			throw new IllegalArgumentException("'email' should NOT be null.");
		}
		int length = email.trim().length();
		if (length == 0) {
			throw new IllegalArgumentException("'email' should NOT be blank.");
		}
	}

	private void _validate(int sizeInPixels) {
		boolean b = sizeInPixels >= 1 && sizeInPixels <= 512;
		if (b == false) {
			throw new IllegalArgumentException(
					"sizeInPixels needs to be between 1 and 512");
		}
	}
	
	private String _md5(String string) {
		MessageDigest md = null;
		try {md = MessageDigest.getInstance("MD5");} catch (NoSuchAlgorithmException e) {}
		md.update(string.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}

