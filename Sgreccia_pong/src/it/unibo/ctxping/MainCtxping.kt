/* Generated by AN DISI Unibo */ 
package it.unibo.ctxping
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
	QakContext.createContexts(
	        "127.0.0.1", this, "pong.pl", "sysRules.pl", "ctxping"
	)
	//JAN Facade
	//JAN24 Display
}

