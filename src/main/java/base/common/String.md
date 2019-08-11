# String源码分析
## 简介
- String是Java开发最常用的类之一，但是很少的人去真正解开他的面纱。
## 源码解析
### 实现接口
<pre>
     <code>public final class String implements java.io.Serializable, Comparable<String>, CharSequence
     </code>
 </pre>
 1. 注意，String类是final类型的，意味着String这个类不能被继承
### 属性
<pre>
     <code>
             /** The value is used for character storage. */

                 private final char value[];

                 /** Cache the hash code for the string */

                 private int hash; // Default to 0
    /**
     * Class String is special cased within the Serialization Stream Protocol.
     *
     * A String instance is written into an ObjectOutputStream according to
     * <a href="{@docRoot}/../platform/serialization/spec/output.html">
     * Object Serialization Specification, Section 6.2, "Stream Elements"</a>
     */

     private static final ObjectStreamField[] serialPersistentFields =
             new ObjectStreamField[0];

     </code>
</pre>
1. 第一个是最主要的，我们创建一个字符串，字符串实际是存在一个字符数组里面
2. hash就是创建String字符串的hash值，serialPersistentFields 是序列化相关的
3. char[] value是String类的核心，字符串的操作都是围绕则这个属性展开的，此属性也是final类型的，
   所以由final的特性可知，String类的char[]类型的value这个属性只能赋值一次。
## 常用方法解析
 - 常用构造函数
    <pre>
         <code>
             public String(String original) {
                      this.value = original.value;
                      this.hash = original.hash;
                  }

                 public String(char value[]) {
                      this.value = Arrays.copyOf(value, value.length);

                      //Arrays.copyOf源码，实际也是调用了System.arraycopy()
                      public static char[] copyOf(char[] original, int newLength) {
                              char[] copy = new char[newLength];
                              System.arraycopy(original, 0, copy, 0,
                                               Math.min(original.length, newLength));
                              return copy;
                          }
                  }

                  public String(char value[]) {
                          this.value = Arrays.copyOf(value, value.length);
                      }

                  public String(int[] codePoints, int offset, int count) {
                  //核心代码---------------
                       final int end = offset + count;
                    // Pass 2: Allocate and fill in char[]
                          final char[] v = new char[n];

                          for (int i = offset, j = 0; i < end; i++, j++) {
                              int c = codePoints[i];
                              if (Character.isBmpCodePoint(c))
                                  v[j] = (char)c;
                              else
                                  Character.toSurrogates(c, v, j++);
                          }

                          this.value = v;
                  }

                  public String(byte bytes[], int offset, int length, Charset charset) {
                          if (charset == null)
                              throw new NullPointerException("charset");
                          checkBounds(bytes, offset, length);
                          this.value =  StringCoding.decode(charset, bytes, offset, length);
                      }
         <code>
    </pre>
    <pre>
           hash map put 注释
           /**
                  * put的hash值算法
                  * static final int hash(Object key) {
                  *         int h;
                  *         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
                  *     }
                  *
                  *     数组中具体hash位置计算:  tab[i = (n - 1) & hash]  ,  n 为数组长度，为2的倍数
                  *
                  *    put步骤：1.判断数组是否为空(第一次)，
                  *              if：1.1若为空，进行扩容
                  *             2.判断数组hash位置是否为空，
                  *                if:2.1若为空，直接new一个Node节点到p=tab[i = (n - 1) & hash]
                  *                else:2.2 若不为空
                  *                         if:2.2.1 判断p位置上的key和value是否和正在put的相等,若相等e=p
                  *                         else if:为红黑树类型，则putTreeVal
                  *                         else:遍历数组:目的是找到为空的节点，然后判断是否要转成红黑树，或者找到key相等的node节点
                  *                             for (int binCount = 0; ; ++binCount) {
                  *                                 2.2.1.1:if:e=p.next为null,则p.next = newNode(hash, key, value, null);
                  *                                         if:Node链表长度大于等于7，转换成红黑树treeifyBin(tab, hash);break;
                  *                                 2.2.1.2:if:找到相等的Node节点了，break;
                  *                                 p = e;
                  *                                 }
                  *                          if:2.2.2 ：e!=null，判断是否要把旧值替换成新值,然后return 旧值
                  *                          判断是否需要扩容 if (++size > threshold){resize();} return null;
                  *
                  *
                  */
    </pre>