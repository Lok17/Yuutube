
package yuutube;


public class Legit {
    	public static boolean check(String s) {
		if(s.length()<5) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!(c == '@' || c == '_' || c == '.' || c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'||c>='0'&&c<='9')) {
				return false;
			}
		}
		
		for (int i = 0; i < s.length(); i++) {
			String sub = s.substring(s.length() - 4, s.length());
			if (!(sub.equals(".com"))) {
				return false;
			}
		}
		
		for (int i = 0; i < s.length(); i++) {
			int n = s.indexOf('@');
			char c1=s.charAt(n-1);
			char c2=s.charAt(n+1);
			if(!(c1>='0'&&c1<='9'||c1>='a'&&c1<='z'||c1>='A'&&c1<='Z')) {
				return false;
			}
			if(!(c2>='0'&&c2<='9'||c2>='a'&&c2<='z'||c2>='A'&&c2<='Z')) {
				return false;
			}
		}
		return true;
	}
}
