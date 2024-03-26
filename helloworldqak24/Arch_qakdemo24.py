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
with Diagram('qakdemo24Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxstreamsdemo', graph_attr=nodeattr):
          qasink=Custom('qasink','./qakicons/symActorSmall.png')
          datasource=Custom('datasource','./qakicons/symActorSmall.png')
          filter=Custom('filter','./qakicons/symActorSmall.png')
     filter >> Edge( label='data', **eventedgeattr, decorate='true', fontcolor='red') >> qasink
     datasource >> Edge( label='data', **eventedgeattr, decorate='true', fontcolor='red') >> filter
diag
