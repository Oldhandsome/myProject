cloudnote 云笔记:两种解决方法 
1.Spring+SpringMVC+Mybatis 
2.Spring+Struts2+hiberate

1).
由三层组成:
- 表现层  使用springmvc框架
- 业务层
- 数据层  使用mybatis框架
- 整体的框架有spring来控制完成。
- 功能：实现云笔记系统的登录，笔记的CRUD,笔记本的CRUD，笔记的收藏和取消收藏，笔记的批量回收，删除，移动、回收，以及用户信息的修改。

该系统也是我自助学习完SSM框架之后，自己使用mvc结构和SSM框架，自己独立自助地开发了一个小型的网站（用了Hui框架的几个网页，我使用了使用了Jquery和layer框
架以及ajax来完成网站的动态展示，且后端的逻辑和代码以及base.js均为本人独立所写）。

2).
- 表现层  struts2来完成
- 业务层 
- 数据层 hibernate完成
- 功能：功能并未做什么改变.

心得：使用不同的框架完成相同的东西，功能看似都没发生变化，但在ssh中的代码量却增加了不少；尤其是在hibernate方面，实在是有点头痛，虽然不需要写sql语句，
直接使用hibernateTemplate提供的面对对象的增、删、改、查，确实很方便；但是遇到批量删除，批量修改，多表连接查询并返回一个列表对象（对象中包含不同表的
列），都很让人头痛（又不想多写大段的代码），只能通过hibernateTemplate直接操作jdbc完成数据的修改。对于简单的CRDU操作，hibernate比mybatis好一点，毕竟
不用写sql（不过hibernate的配置文件还是有点多的，还好能自动生成）。对于struts2和springmvc两者来说：两者的功能大同小异（struts2绝大部分利用filter来完
成工作，而springmvc则是通过servlet来完成工作），都是利用反射，实现请求参数的注入（不过，如果struts2的action请求参数不能复用，就也很无语......写一大堆
的get/set方法，请求参数的获取都能有一屏幕），虽然这一点和springmvc按属性名利用bean对象的自动注入相似，但这么多的get/set也有点吃不消。但也有不同之
处：struts2的action必须是多例的，而springmvc中的控制器是单例的，并且struts2每次接受一个请求都会创建一个action，去处理请求，而spring则将请求和
controller中的方法对应（和servlet判断请求，执行对应的方法类似）。
并且struts2的配置文件struts.xml中需要重复配置InterceptorStack，每个package都要重复配置重复的interceptor，相对于spring的配置文件确实多了点。
