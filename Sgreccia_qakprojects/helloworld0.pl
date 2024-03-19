%====================================================================================
% helloworld0 description   
%====================================================================================
request( req, requesting(STR) ). %formato di richiesta Prod -> Cons
%====================================================================================
context(ctxhello, "localhost",  "TCP", "8000").
 qactor( consumer, ctxhello, "it.unibo.consumer.Consumer").
 static(consumer).
  qactor( producer, ctxhello, "it.unibo.producer.Producer").
 static(producer).
