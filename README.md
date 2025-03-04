1 App的运行与开发环境

（1）	运行环境： 6.0以上版本Android的Android手机/平板

（2）	部署方法： 直接安装即可

（3）	开发环境： Android Studio 3.1.2, IntelliJ IDEA 2018.1

（4）	手写代码行数：约1400行（Java/Kotlin）


2 App架构设计及技术实现方案
   		（1）App中主要设计了ChargeActivity、Chart1Activity、Location、LoginActivity、RegisterActivity、StickyActivity等类，它们之间比如MainActivity、Chart1Activity、ChargeActivity它们和BaseActivity是继承关系。我将这些类划分成了activity、adapter、base、dao、db、entity、fragment、utils等包
     
		（2）组件化开发：我使用了textview，edittext，button，recyclerview，imageview组件，组件之间单独使用，没有依赖关系。
  
3 技术亮点、技术难点、出现的问题

	技术亮点在于安卓中SQLite数据库的使用。App中的注册登录操作等实现了对数据库中数据的创建和增删改查功能。技术难点也同时在于SQLite。在设计注册登录一开始时出现了注册失败和登录显示账号不存在等bug，后来经过反复测试得以排除此问题。
 
4 简要开发过程

3月21号	查找资料确定要使用的算法与数据结构

4月3号	完成系统基本功能设计

4月5号   进行便签、注册登录功能开发

4月25号	各功能开发完成，进入测试阶段

5月1号	测试基本完毕，可以正常运行，进行收尾工作

5月12号  程序开发工作完毕，编写及整理文档

5 App功能说明：
	![image](https://github.com/user-attachments/assets/3cc45c15-daa8-4990-8ae9-c2d5c56a7fa5)
 
  ![image](https://github.com/user-attachments/assets/1a699532-7ded-4a9b-b967-5313b2862357)
  
  ![image](https://github.com/user-attachments/assets/a9338f83-90fe-4751-8dc0-0d9dd12b6758)
  
  ![image](https://github.com/user-attachments/assets/49a9dda4-a7eb-462b-abcf-b883daf88a82)
  
  ![image](https://github.com/user-attachments/assets/f32e2f6e-e756-4b61-83a8-a9afa102407c)
  
  ![image](https://github.com/user-attachments/assets/69be96ed-5ee3-48c4-8eb9-4ae9994022ab)
  
  ![image](https://github.com/user-attachments/assets/fca9c6f3-69d4-4e08-8838-fe7a2e505570)
  
