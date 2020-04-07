# 爬虫





## 功能实现

爬取拉勾网: https://www.lagou.com/gongsi/ 的公司信息

## 实现步骤

1. 在IDEA新建一个maven项目

2. 导入maven的selenium-server，dependency代码块

   ```
       <dependency>
           <groupId>org.seleniumhq.selenium</groupId>
           <artifactId>selenium-server</artifactId>
           <version>4.0.0-alpha-2</version>
       </dependency>
   ```

   

3. 下载chromeDriver驱动，通过chromeDrive模拟点击操作

4. 通过XPath语法抓取页面元素，筛选条件抓取页面的信息——公司名称

5. 将满足条件的公司输出保存到txt文件

## Tips

1. 抓取页面时点击下一页的实现用到了线程，Thread.Sleep()，以免页面还没有跳转成功就开始抓取
2. 输出到文件用到字符流输出：FileWriter()。



## 待完善

1. 暂时还无法解决页面弹窗的问题，如果页面出现广告弹窗等将无法继续抓取页面
2. 有想法可以直接将抓取结果输出到MySql数据库生成表来存储分析，后续可以改进一下
3. 因为只是练习所以抓取的数据较为单一，如果比较复杂的话，应当对数据进行分组等操作
