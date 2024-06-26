/* Generated by AN DISI Unibo */ 
package it.unibo.referee

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Referee ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 var X = -1  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outmagenta("$name started")
						observeResource("127.0.0.1","8014","ctxping","ping","ballview")
						observeResource("127.0.0.1","8015","ctxpong","pong","ballview")
						delay(700) 
						CommUtils.outred("$name sending game STARTS")
						emit("startgame", "start(0)" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="handleUpdate",cond=whenDispatch("ballview"))
				}	 
				state("handleUpdate") { //this:State
					action { //it:State
						CommUtils.outblue("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="handleUpdate",cond=whenDispatch("ballview"))
					transition(edgeName="t02",targetState="endgame",cond=whenEvent("miss"))
				}	 
				state("endgame") { //this:State
					action { //it:State
						CommUtils.outred("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						CommUtils.outred("$name game ENDS!")
						if( checkMsgContent( Term.createTerm("miss(N)"), Term.createTerm("miss(N)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 X = payloadArg(0).toInt()  
								CommUtils.outmagenta("$name victory goes to $X")
								delay(3000) 
								emit("endgame", "end($X)" ) 
						}
						delay(500) 
						 System.exit(0)  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
