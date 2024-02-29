package main;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.BasicMsgUtil;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class ServiceCallerTCPwithFactory {

	public static void main(String[] args) {
		int numToReq = 7;

		String msgid = "dofibo";
		String msgcontent = "dofibo(" + numToReq + ")";
		String serviceToRequest = "servicemath";
		IApplMessage message = BasicMsgUtil.buildRequest("clientJava", msgid, msgcontent, serviceToRequest);

//		try {
//			boolean isSync = Boolean.parseBoolean(args[0]);
//		} catch (Exception e) {
//			System.err.println("Scemo");
//		}

		ProtocolType proto = ProtocolType.tcp;
		String hostAddr = "130.136.113.239";
		String entry = "8011";
		Interaction conn = ConnectionFactory.createClientSupport(proto, hostAddr, entry);

		// Request SINCRONA
		try {
			CommUtils.outblue(proto + " | Richiesta Sincrona: richiesta = " + message);
			IApplMessage response = conn.request(message);
			CommUtils.outmagenta(proto + " | Richiesta Sincrona: risposta = " + response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		numToReq = 3;
		msgcontent = "dofibo(" + numToReq + ")";
		message = BasicMsgUtil.buildRequest("clientJava", msgid, msgcontent, serviceToRequest);

		try {
			CommUtils.outblue(proto + " | Richiesta Asincrona: richiesta = " + message);
			conn.forward(message);
			System.out.println("In attesa di risposta...");
			IApplMessage response = conn.receive();
			CommUtils.outmagenta(proto + " | Richiesta Asincrona: risposta = " + response);

		} catch (Exception e) {
			e.printStackTrace();
		}

//		message = BasicMsgUtil.buildRequest(null, null, null, null)
	}

}
