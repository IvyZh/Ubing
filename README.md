# Ubing
first commit.


### 2018.12.14

- AS 项目关联Github 和 解除Github关联 https://www.jianshu.com/p/8cd7f2f64379
- 完成日志L.java  在BaseLibrary里面
- 完成简单IOC注解 框架，目前包含CheckNet、ViewById、OnClick，在BaseLibrary里面
    - mViewFinder.getContext() 这个方法后面需要优化，TODO
- 完成BaseActivity
- 完成简单的网络引擎，链式调用
- Bmob
    - 控制台：https://www.bmob.cn/app/list
    - 快速入门：http://doc.bmob.cn/data/android/
    - 开发文档：http://doc.bmob.cn/data/android/develop_doc/#2
    - Restful API文档：https://docs.bmob.cn/data/Restful/a_faststart/doc/index.html
- RecyclerView
    - 通用的Adapter（CommonRecyclerAdapter）
    - 通用的ViewHolder（CommonViewHolder）
    - 条目的点击事件
    - 分割线问题
    - 多布局显示问题（todo）
    - 添加头部（todo）
    - 刷新（todo）
    - 侧滑删除和拖动排序（todo）
    
- URL编码
    - http://web.chacuo.net/charseturlencode
- Glide4.0后支持圆形图片及其使用方式
    - https://blog.csdn.net/qq_36523667/article/details/79492928
    - compile 'com.github.bumptech.glide:glide:4.1.1'
    - Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);  