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
with Diagram('prodcons24_consumerArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxconsumer', graph_attr=nodeattr):
          consumer=Custom('consumer','./qakicons/symActorSmall.png')
          consumerobserver=Custom('consumerobserver','./qakicons/symActorSmall.png')
     consumer >> Edge(color='blue', style='solid',  decorate='true', label='<info &nbsp; short &nbsp; >',  fontcolor='blue') >> consumerobserver
diag
