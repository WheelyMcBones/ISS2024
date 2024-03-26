### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
with Diagram('prodcons24_producerArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxproducer', graph_attr=nodeattr):
          producer1=Custom('producer1','./qakicons/symActorSmall.png')
          producer2=Custom('producer2','./qakicons/symActorSmall.png')
          otherobserver=Custom('otherobserver','./qakicons/symActorSmall.png')
     with Cluster('ctxconsumer', graph_attr=nodeattr):
          consumer=Custom('consumer(ext)','./qakicons/externalQActor.png')
     producer2 >> Edge(color='magenta', style='solid', decorate='true', label='<distance<font color="darkgreen"> distanceack</font> &nbsp; >',  fontcolor='magenta') >> consumer
     producer1 >> Edge(color='blue', style='solid',  decorate='true', label='<distance<font color="darkgreen"> distanceack</font> &nbsp; >',  fontcolor='blue') >> consumer
     consumer >> Edge(color='blue', style='solid',  decorate='true', label='<short &nbsp; >',  fontcolor='blue') >> otherobserver
diag
