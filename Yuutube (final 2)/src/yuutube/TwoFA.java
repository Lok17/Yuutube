package yuutube;

public class TwoFA {

	public static int Auth(String sendTo) {
		final String title = "Welcome to Yuutube, This is Your Confirmation Code!";
		final String body = "Enjoy! Your Confirmation Code is: ";
		int r = (int) (Math.random() * 10000);
		String code = String.format("%04d", r);
		String codeBody = body + code;
		try {
			MailSender.useYandex(sendTo, title, codeBody);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;

	}

	public static int ResetPassword(String sendTo) {
		final String title = "Hello from Yuutube, This is Your Confirmation Code!";
		final String body = "Your Confirmation Code to Reset your password is: ";
		int r = (int) (Math.random() * 10000);
		String code = String.format("%04d", r);
		String codeBody = body + code;
		try {
			MailSender.useYandex(sendTo, title, codeBody);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;

	}

	public static int ConfirmLogin(String sendTo) {
		final String title = "Are you logging in? This is Your Confirmation Code!";
		final String body = "Your Confirmation Code to Reset your password is: ";
		int r = (int) (Math.random() * 10000);
		String code = String.format("%04d", r);
		String codeBody = body + code;
		try {
			MailSender.useYandex(sendTo, title, codeBody);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;

	}

}