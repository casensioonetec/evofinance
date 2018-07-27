package com.ef.srv.campaigns.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static String getTokenFromRaw(String responseOAuth2) throws UnsupportedEncodingException {
		String response = ""; //$NON-NLS-1$

		final String regexType = "token_type=(\\w*)"; //$NON-NLS-1$
		final Pattern patternType = Pattern.compile(regexType);
		final Matcher matcherType = patternType.matcher(responseOAuth2);

		final String regexToken = "access_token\\=(.*?)\\&"; //$NON-NLS-1$
		final Pattern patternToken = Pattern.compile(regexToken);
		final Matcher matcherToken = patternToken.matcher(responseOAuth2);

		if (matcherToken.find() && matcherType.find()) {
			// We return index 1 because the index 0 includes the delimiters
			response = matcherType.group(1) + " " + URLDecoder.decode(matcherToken.group(1), "UTF-8"); //$NON-NLS-1$
		}
		return response;
	}
}
