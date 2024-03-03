package main;

import unibo.basicomm23.examples.ActorNaiveCaller;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

public class Producer extends ActorNaiveCaller {

	public Producer(String name, ProtocolType protocol, String hostAddr, String entry) {
		super(name, protocol, hostAddr, entry);
		// --> port
	}

	@Override
	// invio richieste
	protected void body() throws Exception {
		String msgid = "id0";
		String msgcontent = "SyncRequest";
		String serviceToRequest = "consumer";
		IApplMessage request;

		// Request SINCRONA
		try {
			request = CommUtils.buildRequest(this.name, msgid, msgcontent, serviceToRequest);
			CommUtils.outblue(this.protocol + " | Richiesta Sincrona: richiesta = " + request.msgContent());
			IApplMessage response = this.connSupport.request(request);
			CommUtils.outmagenta(this.protocol + " | Richiesta Sincrona: risposta = " + response.msgContent());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Request ASINCRONA
		try {
			msgcontent = "AsyncRequest";
			request = CommUtils.buildDispatch(this.name, msgid, msgcontent, serviceToRequest);
			CommUtils.outblue(this.protocol + " | Richiesta Asincrona: richiesta = " + request.msgContent());
			this.connSupport.forward(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
