# [android annotations](https://github.com/excilys/androidannotations)
####android annotations源于java annotations
####java annotations中包含三种类型：SourceCode、Class、Runtime。

* SourceCode  写在源代码中，在编译之时，就被会抛弃掉。
* Class  写在源代码中，在编译过程中，被保留到class文件中。
* Runtime 信息被写入class文件中，在系统运行过程中，被VM提取。通过Reflective API来读取相应的信息。

[详细java annotation 解释](http://blog.csdn.net/blueheart20/article/details/18810693)

#### android annotations是Class类型 
![Aaron Swartz]( https://raw.githubusercontent.com/495055772/BaseProj/master/screenShot/classtype.png)

* 它的原理就是根据注解额外添加编辑代码，生成一个新的源码文件，注解的方式是采用标准的Annotation Processing Tool.
* 利用Java Annotation Processing Tool (APT) 在编译源文件(*.java)之前，通过注解处理器(AnnotationProcessor)解释并处理源文件中的注解，生成 一些新的源文件，APT也会对新生成源文件进行编译，直到没有新的文件生成。新生成的源文件在apt_generated文件夹中。

![Aaron Swartz]( https://raw.githubusercontent.com/495055772/BaseProj/master/screenShot/apt_generated.png)

####综上可知：
  所有使用android annotation的类都会生成新的带_的java文件
####比如
 * 注册你使用过注解的四大主件 都是带有下划线的类
 * fragment等 调用使用过注解的类  都是调用下划线的类
 
 如下图所示 都是生成的源代码带_文件都是继承源代码java类

![Aaron Swartz]( https://raw.githubusercontent.com/495055772/BaseProj/master/screenShot/extends.png)

![Aaron Swartz]( https://raw.githubusercontent.com/495055772/BaseProj/master/screenShot/manifest.png)

## 综上所述 android annotations 不是runtime操作，都在编译期生成新的源文件，对程序运行速度没有影响。



#### 优点描述看代码

[android annotation](wiki: https://github.com/excilys/androidannotations/wiki)

代码简洁 可维护性 150kb以下

* 源文件

https://github.com/495055772/BaseProj/blob/master/projectzero/src/com/projectzero/demo/samplemain/sample/sampleHttp/HttpUtilActivity.java

* 注解生成的文件

https://github.com/495055772/BaseProj/blob/master/projectzero/generated/com/projectzero/demo/samplemain/sample/sampleHttp/HttpUtilActivity_.java


#### IDEA 配置
![Aaron Swartz](https://raw.githubusercontent.com/495055772/BaseProj/master/screenShot/ideaAnnotation.jpg)

![Aaron Swartz](https://raw.githubusercontent.com/495055772/BaseProj/master/screenShot/ideajar.png)



#### [文档](https://github.com/excilys/androidannotations/wiki/AvailableAnnotations)


#### [ISSUE](https://github.com/excilys/androidannotations/wiki/FAQ#import)


