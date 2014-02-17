package net.sourceforge.pmd.lang.java.ast;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.pmd.lang.ast.Node;

public class JJTJavaParserState
{
  private List<Node> nodes;
  private List<Integer> marks;
  private int sp;
  private int mk;
  private boolean node_created;

  public JJTJavaParserState()
  {
    this.nodes = new ArrayList();
    this.marks = new ArrayList();
    this.sp = 0;
    this.mk = 0;
  }

  public boolean nodeCreated()
  {
    return this.node_created;
  }

  public void reset()
  {
    this.nodes.clear();
    this.marks.clear();
    this.sp = 0;
    this.mk = 0;
  }

  public Node rootNode()
  {
    return (Node)this.nodes.get(0);
  }

  public void pushNode(Node n)
  {
    this.nodes.add(n);
    this.sp += 1;
  }

  public Node popNode()
  {
    if (--this.sp < this.mk) {
      this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
    }
    return (Node)this.nodes.remove(this.nodes.size() - 1);
  }

  public Node peekNode()
  {
    return (Node)this.nodes.get(this.nodes.size() - 1);
  }

  public int nodeArity()
  {
    return this.sp - this.mk;
  }

  public void clearNodeScope(Node n)
  {
    while (this.sp > this.mk) {
      popNode();
    }
    this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
  }

  public void openNodeScope(Node n)
  {
    this.marks.add(Integer.valueOf(this.mk));
    this.mk = this.sp;
    n.jjtOpen();
  }

  public void closeNodeScope(Node n, int num)
  {
    this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
    while (num-- > 0) {
      Node c = popNode();
      c.jjtSetParent(n);
      n.jjtAddChild(c, num);
    }
    n.jjtClose();
    pushNode(n);
    this.node_created = true;
  }

  public void closeNodeScope(Node n, boolean condition)
  {
    if (condition) {
      int a = nodeArity();
      this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
      while (a-- > 0) {
        Node c = popNode();
        c.jjtSetParent(n);
        n.jjtAddChild(c, a);
      }
      n.jjtClose();
      pushNode(n);
      this.node_created = true;
    } else {
      this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
      this.node_created = false;
    }
  }
}