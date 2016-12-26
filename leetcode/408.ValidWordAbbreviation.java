public static boolean validWordAbbreviation(String word, String abbr) {
	int count = 0, i = 0, j = 0;
	while(i + count < word.length() && j < abbr.length()){
		if(Character.isDigit(abbr.charAt(j))) {
			count = 10 * count + (abbr.charAt(j++) - '0');
		}
		else {
			i += count;
			count = 0;
			if(word.charAt(i++) != abbr.charAt(j++)) return false;
		}
	}
	return (i + count) == word.length() && j == abbr.length();
}
