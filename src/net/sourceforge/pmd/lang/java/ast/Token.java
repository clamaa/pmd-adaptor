package net.sourceforge.pmd.lang.java.ast;

public class Token
{
  public int kind;
  public int beginLine;
  public int beginColumn;
  public int endLine;
  public int endColumn;
  public String image;
  public Token next;
  public Token specialToken;

  public Object getValue()
  {
    return null;
  }

  public Token()
  {
  }

  public Token(int kind)
  {
    this(kind, null);
  }

  public Token(int kind, String image)
  {
    this.kind = kind;
    this.image = image;
  }

  public String toString()
  {
    return this.image;
  }

  public static Token newToken(int ofKind, String image)
  {
    switch (ofKind)
    {
    case 121:
    case 122:
    case 123:
      return new GTToken(ofKind, image);
    }return new Token(ofKind, image);
  }

  public static Token newToken(int ofKind)
  {
    return newToken(ofKind, null);
  }

  public static final class GTToken extends Token
  {
    public int realKind = 123;

    public GTToken(int ofKind, String image) { super(ofKind, image); }

  }
}