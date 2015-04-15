# BaseProj
####Annotaion 实现的方式

####android annotations源于java annotations
####java annotations中包含三种类型：SourceCode、Class、Runtime。

* SourceCode  写在源代码中，在编译之时，就被会抛弃掉。
* Class  写在源代码中，在编译过程中，被保留到class文件中。
* Runtime 信息被写入class文件中，在系统运行过程中，被VM提取。通过Reflective API来读取相应的信息。

详细java annotation 解释:http://blog.csdn.net/blueheart20/article/details/18810693

#### android annotations是SourceCode类型 
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


