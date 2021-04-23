package net.assuresign.base;

public class Constants {

	public static final String ENV = "qa-test";

	public static  double START_VERSION = 3.3;
	public static  double END_VERSION = 3.7;

	public static  String EMAIL_ACCOUNT_ID;;
	public static  String ENVELOPE_ID = "4058fb9d-699c-4e90-a197-aca7002b5aa8";
	public static  String SIGNED_ENVELOPE_ID = "a6bc4d41-5bf6-4a86-bc97-ab9c00f16bdb";
	public static  String UN_SIGNED_ENVELOPE_ID = "812c9ed9-a2dd-4a6f-b498-acee01201bb3";
	public static  String CANCELLED_ENVELOPE_ID = "e4a49426-39dc-473b-b9a8-ad01005ab16b";
	public static  String EMAIL_ADDRESS = "tushar.behera@cloverbaytechnologies.com";
	public static  String USERNAME = "tushar.behera(qaapi-1)@cloverbaytechnologies.com";
	public static  String TEMPLATE_ID = "1b4bf046-633c-41e3-b74b-ace10124bc7b";
	public static  String envelopeSignerID = "mvadgave@mailinator.com";
	
	public static void loadVariables() {
		if(ENV.equals("qa")){
			EMAIL_ACCOUNT_ID = "95d08adc-df59-4e25-b609-ace600d287c9";
		}
		else if(ENV.equals("qa-test")) {
			EMAIL_ACCOUNT_ID = "a2175501-08c9-429b-9c01-abdb010c46e7";
		}
		else if(ENV.equals("dev")) {
			EMAIL_ACCOUNT_ID = "33b4bcec-ef76-47a2-950a-ace501555e41";
		}
		else if(ENV.equals("qa2")) {
			EMAIL_ACCOUNT_ID = "5ac9c4ad-09c3-43cd-be71-ace600d1f18f";
		}
	}
}
