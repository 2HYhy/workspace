### Spring定义组成
1. spring是一个开源的分层框架，由7个模块组成，依次是spring core(核心容器), spring context, spring orm(对象关系映射), spring mvc, spring web, spring dao, spring aop。   
2. 核心容器定义了创建、配置、管理bean的方式。    

### Spring的Bean
1. 使用java注解和spring自动扫描的功能创建Bean
> @Component用在类声明上，用于告诉spring，其需要为当前类创建一个实例，实例名为当前类名首字母小写的形式。 
> @Autowired用在属性上，spring检测到注解后就会在IOC容器中查找是否有与该属性匹配的类实例，有的话即注入当前属性，没有的话则会报错。   

2. 使用xml配置文件创建Bean 
> 构造器注入(有参构造函数)
> setter注入(无参构造函数+属性的setter方法)

3. 程序员负责开发和配置Bean，Spring容器负责创建Bean。Spring能创建出什么样的Bean，取决于我们在配置文件中的配置。

4. 容器中bean常用的作用域:
> singleton: 整个容器中只有该bean只有一个实例，默认作用域。Spring负责监测bean的状态，维护bean的生命周期行为。                 
> protitype: 每次通过容器的getBean方法，都会产生一个新的bean实例。Spring只负责bean的创建。 

5. IOC(控制反转)和DI(依赖注入)   
> 控制反转涉及3个概念: 应用对象，IOC容器，应用对象所依赖的外部对象         
> 控制反转是指将对象的创建、初始化等一系列工作交给Spring容器。即spring容器将控制对象的生命周期和对象间的关系。(以前，当一个应用对象A需要引用/依赖另一个对象B时，需要自己去创建和销毁B，B的一切由A控制；现在，所有的对象A和B都交由Spring进行控制。)            
> 依赖注入是指Spring向应用对象提供其所需要的外部对象。(应用对象需要某个依赖，不需要自己去实现，只要告诉Spring，Spring准备好该依赖后，送给应用对象。) 
> IOC是从容器的角度说，之前要手动在程序里创建的对象，现在由容器自己去创建；DI是从应用程序的角度说，程序将需要的对象告诉容器，待其创建好后直接返回给自己，程序无需知道该对象是如何创建的。