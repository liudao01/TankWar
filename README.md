# TankWar
TankWar 

坦克大战 
 
我记得很清楚 在我2012年的时候 跟着马士兵老师的视频,一点点学习java基础,再到坦克大战,利用全部时间敲代码.  
花了大概一两个月的时间完成了坦克大战.  
但是很可惜,当初不知道保存自己的代码,写的代码不知道到哪里去了.  
现在马士兵老师重开教学课程, 我决定重拾初心, 继续完成当初的代码.   
并且这次是网络版,可以实现联网对战.  

[TOC]

# 实现画出坦克.  

作业: 根据四个boolean值，计算坦克方向，根据坦克方向和速度，自动移动位置。（假设坦克不能停）
![坦克大战1_移动](https://ws4.sinaimg.cn/large/958c5b69ly1g2iqju2czqg20m90fm74j.gif)


# 实现坦克移动发射子弹  

- 如何定义主战坦克的方向  
Enum Dir
- 根据按键改变主战坦克方向  
setMainTankDir()
根据方向进行坦克的移动 ,   
- 怎么样处理坦克静止状态  
moving = false;  
- 想象如何给出更多坦克，以及子弹  
将坦克封装成类，理解面向对象设计中“封装”的思想 
 
- 用双缓冲解决闪烁问题（不重要）  
repaint - update
截获update  
首先把该画出来的东西（坦克， 子弹）先画在内存的图片中，图片大小和游戏画面一致  
把内存中图片一次性画到屏幕上（内存的内容复制到显存）  
- 打出一颗子弹  
按下Ctrl键，主战坦克打出一颗子弹  
用面向对象的思想考虑  
打出一串子弹  
将子弹装在容器中  
![坦克大战2_子弹以及发射](http://ws2.sinaimg.cn/large/958c5b69ly1g2isdgaixkg20m30gbwg3.gif)

作业：搞出一个排的敌人的坦克，挨排儿干掉他们



