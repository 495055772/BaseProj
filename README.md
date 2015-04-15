# BaseProj
####Annotaion 实现的方式

####android annotations源于java annotations
####java annotations中包含三种类型：SourceCode、Class、Runtime。

* SourceCode  写在源代码中，在编译之时，就被会抛弃掉。
* Class  写在源代码中，在编译过程中，被保留到class文件中。
* Runtime 信息被写入class文件中，在系统运行过程中，被VM提取。通过Reflective API来读取相应的信息。

#### android annotations是SourceCode类型 
* 利用Java Annotation Processing Tool (APT) 在编译源文件(*.java)之前，通过注解处理器(AnnotationProcessor)解释并处理源文件中的注解，生成 一些新的源文件，APT也会对新生成源文件进行编译，直到没有新的文件生成。新生成的源文件在apt_generated文件夹中。

![Aaron Swartz]( https://raw.githubusercontent.com/495055772/BaseProj/master/screenShot/apt_generated.png)
