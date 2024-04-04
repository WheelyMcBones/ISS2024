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
with Diagram('pongArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxpong', graph_attr=nodeattr):
          pong=Custom('pong','./qakicons/symActorSmall.png')
          refereepong=Custom('refereepong','./qakicons/symActorSmall.png')
     with Cluster('ctxping', graph_attr=nodeattr):
          ping=Custom('ping(ext)','./qakicons/externalQActor.png')
     pong >> Edge(color='blue', style='solid',  decorate='true', label='<shot &nbsp; >',  fontcolor='blue') >> ping
     pong >> Edge(color='blue', style='solid',  decorate='true', label='<short &nbsp; >',  fontcolor='blue') >> refereepong
     refereepong >> Edge(color='blue', style='solid',  decorate='true', label='<short &nbsp; >',  fontcolor='blue') >> pong
diag
