/*
 * Copyright 2002-2012 Zhuo Ying. All rights reserved.
 * Email: yingzhor@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetx.ext.gravatar;

/**
 * Through ratings the retrieved avatars can be reduced to the ones
 * appropriate for the targeted audience.
 *
 * These ratings are based on the
 * <i>Motion Picture Association of America film rating system</i>. For more
 * information read the
 * <a href="http://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system">
 * Wikipedia article</a>.
 *
 * The rating descriptions are taken from the Gravatar website.
 *
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.2
 */
public enum Rating {
    /**
     * Allow images for:
     * Suitable for display on all websites with any audience type
     */
    G("g"),
    
    /**
     * Allow images for:
     * May contain rude gestures, provocatively dressed individuals,
     * the lesser swear words, or mild violence.
     */
	PG("pg"),

    /**
     * Allow images for:
     * May contain such things as harsh profanity, intense violence, nudity,
     * or hard drug use.
     */
	R("r"),

    /**
     * Allow images for:
     * May contain hardcore sexual imagery or extremely disturbing violence.
     */
	X("x");

    private String key;

    private Rating(String key) {
        this.key = key;
    }

    /**
     * Retrieve the query parameter value which indicates the desired rating.
     *
     * @return Gravatar rating query parameter value
     */
    String getKey() {
        return key;
    }
}