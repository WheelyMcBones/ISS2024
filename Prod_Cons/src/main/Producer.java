package main;

import unibo.basicomm23.examples.ActorNaiveCaller;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;

public class Producer extends ActorNaiveCaller {

	public Producer(String name, ProtocolType protocol, String hostAddr, String entry) {
		super(name, protocol, hostAddr, entry);
	}
	
	private static int id_msg = 0;
	private static String msgid = "id" + Producer.id_msg;

	@Override
	// corpo del Producer (chiamato da run()) -> invio richieste
	protected void body() throws Exception {
		String msgcontent = "SyncRequest";
		String serviceToRequest = "consumer";
		IApplMessage request, response;

		// Invio di 2 Request SINCRONE
		try {
			request = CommUtils.buildRequest(this.name, msgid, msgcontent, serviceToRequest);
			CommUtils.outblue(this.protocol + " | Richiesta Sincrona da " + this.name + ": richiesta = " + request.msgContent());
			Producer.id_msg++;
			
			response = this.connSupport.request(request);
			CommUtils.outmagenta(this.protocol + " | Richiesta Sincrona da " + this.name + ": risposta = " + response.msgContent());
			
			request = CommUtils.buildRequest(this.name, msgid, msgcontent, serviceToRequest);
			CommUtils.outblue(this.protocol + " | Richiesta Sincrona da " + this.name + ": richiesta = " + request.msgContent());
			Producer.id_msg++;
			
			response = this.connSupport.request(request);
			CommUtils.outmagenta(this.protocol + " | Richiesta Sincrona da " + this.name + ": risposta = " + response.msgContent());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Request ASINCRONA
		try {
			msgcontent = "AsyncRequest";
			request = CommUtils.buildDispatch(this.name, msgid, msgcontent, serviceToRequest);
			CommUtils.outblue(this.protocol + " | Richiesta Asincrona da " + this.name + ": richiesta = " + request.msgContent());
			this.connSupport.forward(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private IApplMessage buildingMsg()

}
