## UML-java

## Overview

This project aims to parse the UML graph, find a proper way to store the basic elements, achive the operations such as find, delet, sort... (based on typical graph agorithms)

#

### UML graph

* Basic UML graph

* UML state graph

* Synchronized UML graph 


#

### Test Example




#### 模型中一共有多少个类

输入指令格式：`CLASS_COUNT`

举例：`CLASS_COUNT`

输出：

- `Total class count is x.` x为模型中类的总数

#### 类中的操作有多少个

输入指令格式：`CLASS_OPERATION_COUNT classname mode`

举例：`CLASS_OPERATION_COUNT Elevator NON_RETURN`

输出：

- `Ok, operation count of class "classname" is x.` `x`为模型中参数个数（不包括return）大于等于1的操作个数
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

说明：

- `mode`表示查询类型，数据类型为`OperationQueryType`，取值为：
  - `NON_RETURN` 无返回值操作数量
  - `RETURN` 有返回值操作数量
  - `NON_PARAM` 无传入参数操作数量
  - `PARAM` 有传入参数操作数量
  - `ALL` 全部操作数量
- 此外，本指令中统计的一律为此类自己定义的操作，不包含其各级父类所定义的操作

#### 类中的属性有多少个

输入指令格式：`CLASS_ATTR_COUNT classname mode`

举例：`CLASS_ATTR_COUNT Elevator SELF_ONLY`

输出：

- `Ok, attribute count of class "classname" is x.` `x`为类中属性的个数
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

说明：

- `mode`表示查询的模式，数据类型为`AttributeQueryType`，取值为：
  - `ALL` 全部属性数量（包括各级父类定义的属性）
  - `SELF_ONLY` 此类自身定义的属性数量

#### 类有几个关联

输入指令格式：`CLASS_ASSO_COUNT classname`

举例：`CLASS_ASSO_COUNT Elevator`

输出：

- `Ok, association count of class "classname" is x.` `x`为类关联的个数
  - 如果出现自关联行为的话，也算在内
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

#### 类的关联的对端是哪些类

输入指令格式：`CLASS_ASSO_CLASS_LIST classname`

举例：`CLASS_ASSO_CLASS_LIST Elevator`

输出：

- `Ok, associated classes of class "classname" are (A, B, C).` A、B、C为类所有关联的对端的类名，其中
  - 传出列表时可以乱序，官方接口会自动进行排序（但是需要编写者自行保证不重不漏）
  - 如果出现自关联的话，那么自身类也需要加入输出
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

#### 类的操作可见性

输入指令格式：`CLASS_OPERATION_VISIBILITY classname methodname`

举例：`CLASS_OPERATION_VISIBILITY Taxi setStatus`

输出：

- `Ok, operation visibility of method "methodname" in class "classname" is public: xxx, protected: xxx, private: xxx, package-private: xxx.` 该操作的实际可见性统计
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

说明：

- 本指令中统计的一律为此类自己定义的操作，不包含其各级父类所定义的操作
- 在上一条的前提下，需要统计出全部的名为methodname的方法的可见性信息

#### 类的属性可见性

输入指令格式：`CLASS_ATTR_VISIBILITY classname attrname`

举例：`CLASS_ATTR_VISIBILITY Taxi id`

输出：

- `Ok, attribute "attrname" in class "classname"'s visibility is public/protected/private/package-private.` 该属性的实际可见性
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个
- `Failed, attribute not found.` 类中没有该属性
- `Failed, duplicated attribute.` 类中属性存在多个同名

说明：

* 本指令的查询均需要考虑属性的继承关系。
* 其中对于父类和子类均存在此名称的属性时，需要按照`duplicated attribute`处理。

#### 类的顶级父类

输入指令格式：`CLASS_TOP_BASE classname`

举例：`CLASS_TOP_BASE AdvancedTaxi`

输出：

- `Ok, top base class of class "classname" is top_classname.`  `top_classname`为顶级父类
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

说明：

- 具体来说，对于类$X$，如果$Y$为其顶级父类的话，则满足
  - $X$是$Y$的子类（此处特别定义，$X$也是$X$的子类）
  - 不存在类$Z$，使得$Y$是$Z$的子类

#### 类实现的全部接口

输入指令格式：`CLASS_IMPLEMENT_INTERFACE_LIST classname`

举例：`CLASS_IMPLEMENT_INTERFACE_LIST Taxi`

输出：

- `Ok, implement interfaces of class "classname" are (A, B, C).` A、B、C为继承的各个接口
  - 传出列表时可以乱序，官方接口会自动进行排序（但是需要编写者自行保证不重不漏）
  - 特别值得注意的是，无论是直接实现还是通过父类或者接口继承等方式间接实现，都算做实现了接口
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

#### 类是否违背信息隐藏原则

输入指令格式：`CLASS_INFO_HIDDEN classname`

举例：`CLASS_INFO_HIDDEN Taxi`

输出：

- `Yes, information of class "classname" is hidden.` 满足信息隐藏原则。
- `No, attribute xxx in xxx, xxx in xxx, are not hidden.` 不满足信息隐藏原则。
- `Failed, class "classname" not found.` 类不存在
- `Failed, duplicated class "classname".` 类存在多个

*注意：类图部分的查询命令和上次作业的完全相同，这意味着本次的公测仍然可能使用上次作业用到的公测用例，甚至增加一些新的公测用例。同学们务必针对上次作业中没有通过的测试用例，找到bug并修复，要不然可能会导致重复失分。*

说明：

- 信息隐藏原则，指的是**在类属性的定义中，不允许使用private以外的任何可见性修饰**
- 本指令中需要列出全部的非隐藏属性，同时也需要考虑继承自父类的非隐藏属性
- 值得注意的是，父类和子类中，是可以定义同名属性的（甚至还可以不同类型，不同可见性，感兴趣的话可以自己尝试尝试），然而**父类中定义的和子类中定义的实际上并不是同一个属性，需要在输出时进行分别处理**
- 同样的，返回的列表可以乱序，官方接口会进行自动排序（但是依然需要编写者保证不重不漏）

### 关于UML状态图的查询指令

#### 给定状态机模型中一共有多少个状态

输入指令格式：`STATE_COUNT statemachine_name` 

举例：`STATE_COUNT complex_sm`

输出：

- `Ok，state count of statemachine "complex_sm" is x.` x为应状态机模型complex_sm的状态总数.
- `Failed, statemachine "complex_sm" not found.` 未找到状态机模型complex_sm
- `Failed, duplicated statemachine "complex_sm".` 存在多个状态机模型complex_sm

#### 给定状态机模型中一共有多少个迁移

输入指令格式：`TRANSITION_COUNT statemachine_name`

举例：`TRANSITION_COUNT complex_sm`

输出：

- `Ok, transition count of statemachine "complex_sm" is x.` `x`为状态机模型complex_sm中的迁移个数.
- `Failed, statemachine "complex_sm" not found.` 未找到状态机模型complex_sm
- `Failed, duplicated statemachine "complex_sm".` 存在多个状态机模型complex_sm

#### 给定状态机模型和其中的一个状态，有多少个不同的后继状态

输入指令格式：`SUBSEQUENT_STATE_COUNT statemachine_name statename `

举例：`SUBSEQUENT_STATE_COUNT complex_sm openned`

输出：

- `Ok, subsequent state count from state "openned" in statemachine "complex_sm" is x.` `x`为状态机模型complex_sm中从openned状态可达的不同状态个数
- `Failed, statemachine "complex_sm" not found.` 未找到状态机模型complex_sm
- `Failed, duplicated statemachine "complex_sm".` 存在多个状态机模型complex_sm
- `Failed, state "openned" in statemachine "complex_sm" not found.`在状态机模型complex_sm中未找到状态openned
- `Failed, duplicated state "openned" in statemachine "complex_sm".`在状态机模型complex_sm中存在多个openned状态

说明：

- 本次作业给定的状态机模型中不包含复合状态

### 关于UML顺序图的查询指令

#### 给定UML顺序图，一共有多少个参与对象

输入指令格式：`PTCP_OBJ_COUNT umlinteraction_name`

举例：`PTCP_OBJ_COUNT normal`

输出：

- `Ok, participant count of umlinteraction "normal" is x.` `x`为顺序图模型normal（UMLInteraction）中的参与对象个数（UMLLifeline）
- `Failed, umlinteraction "normal" not found.` 不存在normal这个顺序图模型
- `Failed, duplicated umlinteraction "normal".` 存在多个normal顺序图模型

#### 给定UML顺序图，一共有多少个交互消息

输入指令格式：`MESSAGE_COUNT umlinteraction_name`

举例：`MESSAGE_COUNT normal`

输出：

- `Ok, message count of umlinteraction "normal" is x.` `x`为顺序图模型normal（UMLInteraction）中的消息个数（UMLMessage，不考虑消息内容是否相同）
- `Failed, umlinteraction "normal" not found.` 不存在normal这个顺序图模型
- `Failed, duplicated umlinteraction "normal".` 存在多个normal顺序图模型

#### 给定UML顺序图和参与对象，有多少个incoming消息

输入指令格式：`INCOMING_MSG_COUNT umlinteraction_name lifeline_name`

举例：`INCOMING_MSG_COUNT normal door`

输出：

- `Ok, incoming message count of lifeline "door" in umlinteraction "normal" is x.` x为顺序图模型normal（UMLInteraction）中发送给door的消息个数
- `Failed, umlinteraction "normal" not found.` 不存在normal这个顺序图模型
- `Failed, duplicated umlinteraction "normal".` 存在多个normal顺序图模型
- `Failed, lifeline "door" in umlinteraction "normal" not found.`在顺序图模型normal中未找到参与对象door
- `Failed, duplicated lifeline "door" in umlinteraction "normal".`在顺序图模型normal中存在多个door参与对象

注意：

* 这里的UMLInteraction指UML所定义的一个类型，见第14讲ppt的第16页

### 模型有效性检查

模型有效性检查部分，将在**实例化完毕后自动按序触发执行**，不通过指令的形式。且一旦**发现不符合规则的情况，将直接退出，不进行后续有效性检查和指令查询**。

#### R001：针对下面给定的模型元素容器，不能含有重名的成员(UML002)

- 规则解释：

  - 针对类图中的类（UMLClass），其成员属性（UMLAttribute）和关联对端所连接的UMLAssociationEnd不能有重名
- 输出：

  - 如未发现此问题，则不需要任何输出
  - `Failed when check R001, "member" in "Container", "member2" in "AnotherContainer" has duplicate name.` 发现重名

- 说明：
  - 如果模型中有多个模型元素违背R001，则依次输出，次序不敏感，接口会在输出前进行排序。

#### R002：不能有循环继承(UML008)

- 规则解释：
  - 该规则只考虑类的继承关系、类和接口之间实现关系，以及接口之间的继承关系。所谓循环继承，就是按照继承关系形成了环。
  - 例如下面的场景

```java
interface A extends B {
    // something here
}

interface B extends A{
    // something here
}
```

这里就构成了一组最简单的循环继承。

- 输出：
  - 如未发现此问题，则不需要任何输出
  - `Failed when check R002, class/interface (A, B, C, D) have circular inheritance.` 列出所有在循环继承链中的类或接口名
- 说明：
  - 输出的集合中需要包含全部继承环上的类、接口。
  - 对于同一个类、接口，只需要输出一次即可。

#### R003：任何一个类或接口不能重复继承另外一个接口(UML009)

- 规则解释：
  - 该规则考虑类之间的继承关系、接口之间的继承关系，以及类对接口的实现关系，包括直接继承或间接继承。
  - 例如下面的场景

```java
interface A {
    // something here
}

interface B extends A {
    // something here
}

interface C extends A, B {
    // something here
}

class D implements C {
    // something here
}
```

接口$C$就重复继承了接口$A$（一次直接继承，一次通过接口$B$间接继承），同时类$D$也重复继承了接口$A$（通过接口$C$间接继承）

-   输出：
  - 如未发现此问题，则不需要任何输出
  - `Failed when check R003, class/interface (A, B, C, D) have duplicate inheritance.` 列出所有带有重复继承的类名
-   说明：
    -   如果存在多个直接或间接重复继承了其他的类或接口的类或接口，则按照任意顺序传出即可，次序不敏感，接口会在输出前进行排序。
    -   值得注意的是，**本次作业的本条限制，同样也禁止了接口的重复继承**。然而接口重复继承在Java 8中实际上是允许的，也就是说，这是UML本身的一条合法性规则，无关语言。请各位判断的时候务必注意这件事。

